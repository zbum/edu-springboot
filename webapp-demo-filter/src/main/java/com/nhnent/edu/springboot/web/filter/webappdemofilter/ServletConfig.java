package com.nhnent.edu.springboot.web.filter.webappdemofilter;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean log1ServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new Log1Servlet());
        servletRegistrationBean.setUrlMappings(Arrays.asList("/log1"));
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean log2ServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new Log2Servlet());
        servletRegistrationBean.setUrlMappings(Arrays.asList("/log2"));
        return servletRegistrationBean;
    }
}
