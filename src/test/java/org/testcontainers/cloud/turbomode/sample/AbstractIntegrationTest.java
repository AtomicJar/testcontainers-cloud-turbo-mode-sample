package org.testcontainers.cloud.turbomode.sample;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractIntegrationTest {

    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

    static {
        kafka.start();
    }

    private KafkaConsumer<String, String> createConsumer(String topic) {
        Map<String, Object> kafkaConfig = new HashMap<>();
        kafkaConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
        kafkaConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "tc-" + UUID.randomUUID());
        kafkaConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        kafkaConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        kafkaConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaConfig);
        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }

    protected void writeAndVerify(String clientId, String topic) {
        EventWriter notifier = new EventWriter(kafka.getBootstrapServers(), clientId, topic);
        String notificationMessage = "foobar";
        int messageCount = 100000;
        for (int i = 0; i < messageCount; i++) {
            notifier.sendNotification(notificationMessage);
        }

        // use consumer to check received records
        KafkaConsumer<String, String> consumer = createConsumer(topic);

        AtomicInteger readMessages = new AtomicInteger();
        await().atMost(Duration.of(2, MINUTES)).untilAsserted(() -> {
            ConsumerRecords<String, String> records = consumer.poll(100);
            readMessages.addAndGet(records.count());
            assertEquals(readMessages.get(), messageCount);
        });
    }
}
