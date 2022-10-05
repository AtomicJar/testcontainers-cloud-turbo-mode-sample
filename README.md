# Testcontainers Cloud Turbo Mode Sample
This repository contains tests to be run with [turbo mode](https://knowledge.testcontainers.cloud/turbo-mode)

### Start using Turbo Mode with Testcontainers Cloud
Since version Testcontainers Cloud v1.3.0 you can select the checkbox Turbo mode to enable the mode locally

![enable-turbo-mode](./ui-agent.png)

```
# Clone the repository
git clone https://github.com/AtomicJar/testcontainers-cloud-turbo-mode-sample
cd testcontainers-cloud-turbo-mode-sample/
# run the Gradle build
./gradlew test
```

### How to verify that Turbo mode is enabled?
When you configured turbo mode for Testcontainers Cloud and your build tool, you should see multiple lease allocations in the connection tab of the Testcontainers Cloud application once you run your tests.

![turbo-mode](./turbo-mode.png)