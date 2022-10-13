package org.testcontainers.cloud.turbomode.sample;

import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public class AbstractIntegrationTest {

    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

    static {
        kafka.start();
    }

}
