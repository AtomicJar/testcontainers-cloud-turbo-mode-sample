package org.testcontainers.cloud.turbomode.sample;

import org.junit.jupiter.api.Test;

public class TestcontainersCloud3Test extends AbstractIntegrationTest {

    @Test
    void writeAndVerify() {
        writeAndVerify("clientId3", "topic3", 100000);
    }
}