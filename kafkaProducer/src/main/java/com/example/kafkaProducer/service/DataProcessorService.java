package com.example.kafkaProducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.example.kafkaProducer.models.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class DataProcessorService {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${tpd.topic-name}")
    private String topicName;

    public void Run(Integer count) throws JsonProcessingException {
        Random random = new Random();

        for(int i =0 ;i < count; i++)
        {
            var nextInt = random.nextInt(100);
            var localDate = LocalDate.of(2023, 3, 9);
            DataModel dataModel = new DataModel(i, "abc" + nextInt, localDate.plusDays(nextInt).toString());
            ObjectWriter ow = (ObjectWriter) new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(dataModel);
            sendMessage(json);
        }
    }

    private void sendMessage(String message) {

        CompletableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            else {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
