package org.testcontainers.cloud.turbomode.sample;

import org.junit.jupiter.api.Test;

public class TestcontainersCloud1Test extends AbstractIntegrationTest {

    @Test
    void writeAndVerify() {
        writeAndVerify("clientId1", "topic1", 100000);
    }
}