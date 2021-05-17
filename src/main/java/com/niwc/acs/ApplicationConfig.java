package com.niwc.acs;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig implements
WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>{

	
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8080);
        factory.setContextPath("/api");
     }
}
