package org.testcontainers.cloud.turbomode.sample;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.*;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    protected void writeAndVerify(String clientId, String topic, int messageCount) {
        var allIds = new ArrayList<>();
        var notifier = new EventWriter(kafka.getBootstrapServers(), clientId, topic);
        for (int i = 0; i < messageCount; i++) {
            var uuid = UUID.randomUUID().toString();
            notifier.sendNotification(uuid);
            allIds.add(uuid);
        }

        // use consumer to check received records
        KafkaConsumer<String, String> consumer = createConsumer(topic);
        await().atMost(Duration.of(2, MINUTES)).untilAsserted(() -> {

            consumer.poll(Duration.ofMillis(100))
                    .forEach(each -> allIds.remove(each.value()));

            assertTrue(allIds.isEmpty());
        });
    }
}
