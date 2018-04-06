package com.example.multi.datasource.sample.sharding;

import com.example.multi.datasource.sample.sharding.aspect.ShardingHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myeongju.jung
 */
@Configuration
@Profile("shard")
public class ShardingDataSourceConfig {
    @Bean
    public DataSource shardingDataSource(@Qualifier("firstDataSource") DataSource firstDataSource,
                                        @Qualifier("secondDataSource") DataSource secondDataSource) {
        return new ShardingRoutingDataSource(firstDataSource, secondDataSource);
    }

    @Primary
    @Bean
    public DataSource dataSource(@Qualifier("shardingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Slf4j
    static class ShardingRoutingDataSource extends AbstractRoutingDataSource {
        private final int dataSourcesSize;

        ShardingRoutingDataSource(DataSource... dataSources) {
            this.dataSourcesSize = dataSources.length;
            super.setTargetDataSources(getTargetDataSources(dataSources));
        }

        private Map<Object, Object> getTargetDataSources(DataSource[] dataSources) {
            Map<Object, Object> targetDataSources = new HashMap<>(dataSources.length);
            int index = 0;
            for (DataSource dataSource : dataSources) {
                targetDataSources.put(index++, dataSource);
            }
            return targetDataSources;
        }

        @Override
        protected Object determineCurrentLookupKey() {
            return ShardingHolder.getKey() % dataSourcesSize;
        }
    }
}
