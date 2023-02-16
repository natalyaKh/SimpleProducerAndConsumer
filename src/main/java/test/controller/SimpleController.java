package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.entity.Employee;
import test.service.ProducerService;

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
}
