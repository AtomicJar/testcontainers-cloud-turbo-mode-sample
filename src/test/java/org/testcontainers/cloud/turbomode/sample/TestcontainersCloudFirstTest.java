package org.testcontainers.cloud.turbomode.sample;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import org.junit.jupiter.api.Test;
import org.testcontainers.DockerClientFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class TestcontainersCloudFirstTest {

  @Test
  public void testcontainersCloudDockerEngine() {
    DockerClient client = DockerClientFactory.instance().client();
    Info dockerInfo = client.infoCmd().exec();

    assertThat(dockerInfo.getServerVersion())
      .as("Docker Client is connected to Testcontainers Cloud")
      .contains("testcontainerscloud");

    System.out.println(PrettyStrings.logo);
  }

}