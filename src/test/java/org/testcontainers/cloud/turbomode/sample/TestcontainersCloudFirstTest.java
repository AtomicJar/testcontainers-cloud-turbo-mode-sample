package org.testcontainers.cloud.turbomode.sample;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import org.junit.jupiter.api.Test;
import org.testcontainers.DockerClientFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestcontainersCloudFirstTest {

    @Test
    void testcontainersCloudDockerEngine() {
        DockerClient client = DockerClientFactory.instance().client();
        Info dockerInfo = client.infoCmd().exec();

        assertNotNull(dockerInfo.getServerVersion());
        assertTrue(dockerInfo.getServerVersion().contains("testcontainerscloud"));

        System.out.println(PrettyStrings.logo);
    }

}