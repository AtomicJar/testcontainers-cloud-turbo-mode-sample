package org.testcontainers.cloud.turbomode.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class TestcontainersCloud4Test extends AbstractIntegrationTest {

    @Test
    public void testFindingAnInsertedValue() {
        Assertions.assertNotNull(kafka.getBootstrapServers());
    }
}