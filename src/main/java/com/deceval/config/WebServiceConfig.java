package com.deceval.config;

import com.deceval.infrastructure.input.ProxyServiceController;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.AbstractLoggingInterceptor;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;


/**
 * Esta clase se encarga de la configuracion para poder exponer los servicios
 * @author inerjanuerBernate
 */
@Configuration
public class WebServiceConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringBus springBus;

    @Bean
    public ServletRegistrationBean<CXFServlet> webservices() {
        final ServletRegistrationBean<CXFServlet> servletRegistrationBean = new ServletRegistrationBean<>(new CXFServlet(),"/services/*");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addInitParameter("session-timeout", "900");
        return servletRegistrationBean;
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus springBus = new SpringBus();
        springBus.getInInterceptors().add(loggingInterceptor());
        springBus.getInFaultInterceptors().add(loggingInterceptor());
        springBus.getOutInterceptors().add(logOutInterceptor());
        springBus.getOutFaultInterceptors().add(logOutInterceptor());
        return new SpringBus();
    }

    @Bean
    public Endpoint app() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        Object implementor = new ProxyServiceController();
        Endpoint endpoint = new EndpointImpl(bus, implementor);
        endpoint.publish("/ProxyServicesImplPort");
        return endpoint;
    }

    @Bean
    public LoggingFeature loggingFeature() {
        LoggingFeature logFeature = new LoggingFeature();
        logFeature.setPrettyLogging(true);
        logFeature.initialize(springBus);
        springBus.getFeatures().add(logFeature);
        return logFeature;
    }

    @Bean
    public AbstractLoggingInterceptor loggingInterceptor(){
        LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
        loggingInInterceptor.setPrettyLogging(true);
        return loggingInInterceptor;
    }

    @Bean
    public AbstractLoggingInterceptor logOutInterceptor() {
        LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
        loggingOutInterceptor.setPrettyLogging(true);
        return loggingOutInterceptor;
    }


}
