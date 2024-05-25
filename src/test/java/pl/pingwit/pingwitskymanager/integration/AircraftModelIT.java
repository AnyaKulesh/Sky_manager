package pl.pingwit.pingwitskymanager.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.pingwit.pingwitskymanager.PingwitSkyManagerApplication;
import pl.pingwit.pingwitskymanager.controller.aircraftmodel.AircraftModelDto;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(classes = {PingwitSkyManagerApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class AircraftModelIT {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12");
    @LocalServerPort
    private Integer port;

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getPassword);
        registry.add("spring.datasource.password", postgres::getUsername);
        registry.add("spring.datasource.driver-class-name", postgres::getDriverClassName);
    }

    @Test
    void verifyAircraftModelLifecycle(){
        // input data for aircraft model creation
        String inputName = "Boeing";

        // prepare request
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("admin", "admin");
        HttpEntity<String> request = new HttpEntity<>(headers);

        String aircraftModelUrl = "http://localhost:" + port + "/aircraft/model";

        // aircraft model creation
        ResponseEntity<Integer> createdResponse = restTemplate.exchange(aircraftModelUrl + "?name=" + inputName, HttpMethod.POST, request, Integer.class);

        assertThat(createdResponse.getStatusCode().is2xxSuccessful()).isTrue();
        Integer createdAircraftModelId = createdResponse.getBody();

        // retrieve created aircraft model
        ResponseEntity<AircraftModelDto> response = restTemplate.exchange(aircraftModelUrl + "/" + createdAircraftModelId, HttpMethod.GET, request, AircraftModelDto.class);
        AircraftModelDto aircraftModelDto = response.getBody();

        Assertions.assertNotNull(aircraftModelDto);
        Assertions.assertNotNull(aircraftModelDto.getName());
        assertThat(aircraftModelDto.getName()).isEqualTo(inputName);

        // retrieve a list of aircraft models
        HttpEntity<String> allModelsRequest = new HttpEntity<>(headers);
        ResponseEntity<List<AircraftModelDto>> exchange = restTemplate.exchange(aircraftModelUrl, HttpMethod.GET, allModelsRequest, new ParameterizedTypeReference<List<AircraftModelDto>>() {
        });

        assertThat(exchange.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(Objects.requireNonNull(exchange.getBody())).hasSize(1);
    }
}
