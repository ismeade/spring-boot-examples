package com.ade.exp.camel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by liyang on 17-12-26.
 */
@Component
public class SampleBean {

    @Value("${greeting}")
    private String say;

    public String saySomething() {
        return say;
    }

    public Integer getSecond() {
        return LocalDateTime.now().getSecond();
    }

}
