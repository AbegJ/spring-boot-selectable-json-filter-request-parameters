package com.abegglen

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.http.converter.json.MappingJacksonValue
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ResponseBody
    @RequestMapping("/greeting")
    MappingJacksonValue greeting(
            @RequestParam(value = "name", defaultValue = "World") String name,
            @RequestParam(value = "fullName", defaultValue = "") String fullName,
            @RequestParam(value = "address", defaultValue = "") String address) {

        MappingJacksonValue value = new MappingJacksonValue(new Greeting(id: counter.incrementAndGet(),
                content: String.format(template, name),
                name: fullName));

        if (fullName && !address) {
            value.setSerializationView(Greeting.SummaryWithFullName.class)

        }else if(fullName && address){
            value.serializationView(Greeting.SummaryComplete.class)
        } else {
            value.setSerializationView(Greeting.Summary.class)
        }

        return value

    }
}
