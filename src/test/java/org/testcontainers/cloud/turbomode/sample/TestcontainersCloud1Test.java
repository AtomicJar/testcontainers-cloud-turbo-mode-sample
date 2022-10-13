package org.testcontainers.cloud.turbomode.sample;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestcontainersCloud1Test extends AbstractIntegrationTest {

    @Test
    public void writeAndVerify() {
        writeAndVerify("clientId1", "topic1", 100000);
    }
}