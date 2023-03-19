package test.controller;

import io.confluent.kafka.schemaregistry.json.JsonSchema;
import io.confluent.kafka.schemaregistry.json.JsonSchemaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.entity.Employee;
import test.service.ProducerService;

import java.io.IOException;

@RestController
@RequestMapping("/check")
public class SimpleController {

    @Autowired
    ProducerService producerService;

    @PostMapping("/json")
    public String sendMessageFromJson(@RequestBody String json){
        return producerService.sendMessageFromSimpleJson(json);
    }

    @PostMapping("/object")
    public String sendMessageFromObject(@RequestBody Employee json){
        return producerService.sendMessageFromObject(json);
    }

    @PostMapping("/schema")
    public JsonSchema getJsonSchemaFromObject (@RequestBody Employee empl){
        try {
            JsonSchema sc = JsonSchemaUtils.getSchema(empl);
            System.out.printf(sc.toString());
            return sc;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
