package com.example.multi.datasource.sample.masterslave;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myeongju.jung
 */
@Configuration
@Profile("masterSlave")
public class MasterSlaveDataSourceConfig {

    @Bean
    public DataSource routingDataSource(@Qualifier("firstDataSource") DataSource writeDataSource,
                                        @Qualifier("secondDataSource") DataSource readDataSource) {
        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DbType.MASTER, writeDataSource);
        dataSourceMap.put(DbType.SLAVE, readDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setLenientFallback(false);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);

        return routingDataSource;
    }

    @Primary
    @Bean
    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    enum DbType {
        MASTER,
        SLAVE
    }

    @Slf4j
    static class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
                ? DbType.SLAVE : DbType.MASTER;
        }
    }
}
