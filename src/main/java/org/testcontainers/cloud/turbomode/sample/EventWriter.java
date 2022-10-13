package org.testcontainers.cloud.turbomode.sample;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class EventWriter {

    private final KafkaProducer<String, String> producer;
    private final String topic;

    public EventWriter(String boostrapServers, String clientId, String topic) {
        this.topic = topic;

        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
        props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, clientId);

        this.producer = new KafkaProducer<>(props,
                new StringSerializer(),
                new StringSerializer());
    }

    public void sendNotification(String notification) {
       producer.send(new ProducerRecord<>(topic, notification));
    }
}