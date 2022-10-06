package org.testcontainers.cloud.turbomode.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class TestcontainersCloud2Test {

    @Container
    private KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

    @Test
    public void testFindingAnInsertedValue() {
        Assertions.assertNotNull(kafka.getBootstrapServers());
    }
}