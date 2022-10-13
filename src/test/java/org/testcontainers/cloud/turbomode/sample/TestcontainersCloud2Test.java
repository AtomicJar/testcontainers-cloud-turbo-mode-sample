package org.testcontainers.cloud.turbomode.sample;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestcontainersCloud2Test extends AbstractIntegrationTest {

    @Test
    public void writeAndVerify() {
        writeAndVerify("clientId2", "topic2");
    }
}