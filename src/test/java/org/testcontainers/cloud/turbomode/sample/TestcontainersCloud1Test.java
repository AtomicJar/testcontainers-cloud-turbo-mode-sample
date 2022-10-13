package org.testcontainers.cloud.turbomode.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestcontainersCloud1Test extends AbstractIntegrationTest {

    @Test
    public void testFindingAnInsertedValue() {
        Assertions.assertNotNull(kafka.getBootstrapServers());
    }
}