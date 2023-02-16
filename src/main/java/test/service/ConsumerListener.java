package test.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import test.config.KafkaConfiguration;
import test.entity.Employee;

@Service
public class ConsumerListener {

    @KafkaListener(topics = KafkaConfiguration.TOPIC_NAME, groupId = "SomeGroup")
    public void consumeJson(ConsumerRecord<Integer, Employee> json){
        System.out.println( "==============consumer message --> " + json.toString() +
                "============================");
    }
}
