package org.testcontainers.cloud.turbomode.sample;

import org.junit.jupiter.api.Test;

public class TestcontainersCloud4Test extends AbstractIntegrationTest {

    @Test
    void writeAndVerify() {
        writeAndVerify("clientId4", "topic4", 100000);
    }
}