package com.grantlee289.spring.routes;

import com.grantlee289.spring.Application;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(
    properties = {"endpoint.from=direct:from", "endpoint.to=mock:to"})
public class FileMoverRouteTest {

  private static final String MOCK_PRODUCER = "direct:from";

  private static final String MOCK_ENDPOINT = "mock:to";

  @Autowired CamelContext context;

  @EndpointInject(MOCK_PRODUCER)
  ProducerTemplate producer;

  @EndpointInject(MOCK_ENDPOINT)
  private MockEndpoint mockEndpoint;

  @Test
  public void sendMessage() throws InterruptedException {
    mockEndpoint.expectedMessageCount(1);
    producer.sendBody("Foo");
    MockEndpoint.assertIsSatisfied(context);
  }
}
