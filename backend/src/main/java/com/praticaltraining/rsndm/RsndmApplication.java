package com.praticaltraining.rsndm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RsndmApplication {
    public static void main(String[] args) {SpringApplication.run(RsndmApplication.class, args);}
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory (){

        // 修改内置的 tomcat 容器配置
        TomcatServletWebServerFactory tomcatServlet = new TomcatServletWebServerFactory();
        tomcatServlet.addConnectorCustomizers(
                (TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "\\")
        );

        return tomcatServlet ;
    }
}

