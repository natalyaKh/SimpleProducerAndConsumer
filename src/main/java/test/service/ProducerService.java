package test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;
import test.config.KafkaConfiguration;
import test.entity.Employee;
import io.confluent.kafka.serializers.json.*;
import java.util.Properties;

@Service
public class ProducerService {
    ObjectMapper om = new ObjectMapper();

    public String sendMessageFromSimpleJson(String json){
        Properties properties = createProducer();
        try{
            KafkaProducer<Integer, Employee> producer = new KafkaProducer<Integer, Employee>(properties);
            Employee message = null;

            message = om.readValue(json, Employee.class);

            ProducerRecord<Integer, Employee> record = new ProducerRecord<>(
                    KafkaConfiguration.TOPIC_NAME, 1, message);
            producer.send(record, (recordMetadata, e) -> {
                if(e == null){
                    System.out.print("SUCCESS!");
                    System.out.print(recordMetadata.toString());
                }else{
                    e.printStackTrace();
                }
            });
            producer.flush();
            producer.close();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return "OK";
    }

    public String sendMessageFromObject(Employee employee){
        Properties properties = createProducer();

            KafkaProducer<Integer, Employee> producer = new KafkaProducer<Integer, Employee>(properties);
            Employee message = null;

            ProducerRecord<Integer, Employee> record = new ProducerRecord<>(
                    KafkaConfiguration.TOPIC_NAME, 1, employee);
            producer.send(record, (recordMetadata, e) -> {
                if(e == null){
                    System.out.print("SUCCESS!");
                    System.out.print(recordMetadata.toString());
                }else{
                    e.printStackTrace();
                }
            });
            producer.flush();
            producer.close();
        return "OK";
    }
    private Properties createProducer() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfiguration.BOOTSTRAP);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
//        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
        properties.setProperty(KafkaJsonSchemaSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, KafkaConfiguration.SHEMA_REGISTRY_URL);
        properties.put(KafkaJsonSchemaSerializerConfig.AUTO_REGISTER_SCHEMAS, false);
        return properties;
    }
}
