package com.ade.exp.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by liyang on 17-12-26.
 */
@Component
public class SampleCamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:hello?period={{timer.period}}")
                .routeId("hello")
                //.transform().method("sampleBean", "saySomething")
                .transform().method("sampleBean", "getSecond")
                //.filter(simple("${body} contains '1'"))
                .filter(exchange -> exchange.getIn().getBody(Integer.class) % 2 == 0)
                .process(exchange -> exchange.getIn().setBody(String.valueOf(exchange.getIn().getBody(Integer.class))))
                .to("stream:out")
                .end()
                //.to("stream:out")
        ;
    }

}
