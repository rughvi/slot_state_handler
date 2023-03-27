package com.example.kafkaConsumer.kafka;

import com.example.kafkaConsumer.models.PersonModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class KafkaTopicListener {

    @Value("${tpd.topic-name}")
    private String topicName;

    @KafkaListener(topics = "${tpd.topic-name}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){

        try {
            System.out.println(payload.value());
            PersonModel personModel = PersonModel.convertFromXml(payload.value());
            System.out.println(personModel.getId());
            System.out.println(personModel.getName());
            System.out.println(personModel.getDOB());
        } catch (Exception e) {
            System.out.println("Error occured while parsing string to DataModel");
            System.out.println(e);
        }
    }

}
