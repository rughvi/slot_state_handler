package com.example.kafkaConsumer.kafka;

import com.example.kafkaConsumer.models.DataModel;
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

        var or = new ObjectMapper();
        try {
            DataModel dataModel = or.readValue(payload.value(), DataModel.class);
            System.out.println(dataModel.getId());
            System.out.println(dataModel.getName());
            System.out.println(dataModel.getDOB());
        } catch (IOException e) {
            System.out.println("Error occured while parsing string to DataModel");
            System.out.println(e);
        }
    }

}
