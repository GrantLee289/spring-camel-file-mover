package com.grantlee289.springcamel.routes;

import com.grantlee289.springcamel.config.ApplicationProperties;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileMoverRoute extends RouteBuilder {

  ApplicationProperties applicationProperties;

  public FileMoverRoute(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  public void configure() throws Exception {
    from(applicationProperties.getFromEndpoint())
        .log(LoggingLevel.INFO, "Received Message: \n ${body}")
        .to(applicationProperties.getToEndpoint())
        .log(LoggingLevel.INFO, "Sent message with body: \n ${body} \n to: " + applicationProperties.getToEndpoint());
  }
}
