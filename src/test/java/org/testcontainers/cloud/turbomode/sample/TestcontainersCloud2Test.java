package org.testcontainers.cloud.turbomode.sample;

import org.junit.jupiter.api.Test;

public class TestcontainersCloud2Test extends AbstractIntegrationTest {

    @Test
    void writeAndVerify() {
        writeAndVerify("clientId2", "topic2", 100000);
    }
}