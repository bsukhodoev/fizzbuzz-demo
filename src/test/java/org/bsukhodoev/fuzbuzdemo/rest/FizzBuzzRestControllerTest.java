package org.bsukhodoev.fuzbuzdemo.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.POST;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FizzBuzzRestControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGoodRequest() {
        FizzBuzzRequest request = new FizzBuzzRequest(Arrays.asList(1, 2, 3, 4, 5, 14, 15, 16));

        FizzBuzzResponse response = restTemplate.postForObject(getUrl(), request, FizzBuzzResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getResult()).isNotEmpty();
        assertThat(response.getResult()).isEqualTo(Arrays.asList("1", "2", "fizz", "4", "buzz", "14", "fizz buzz", "16"));
    }

    @Test
    public void testNullRequest() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        assertThat(
                restTemplate.exchange(new RequestEntity(headers, POST, new URI(getUrl())), FizzBuzzResponse.class)
                    .getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testWrongRequest() {
        assertThat(postForStatusCode(new FizzBuzzRequest())).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(postForStatusCode(new FizzBuzzRequest(new ArrayList<>()))).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(postForStatusCode(new FizzBuzzRequest(Arrays.asList(1, null, 2)))).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(postForStatusCode(new FizzBuzzRequest(singletonList(null)))).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(postForStatusCode(new FizzBuzzRequest(singletonList(-1)))).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(postForStatusCode(new FizzBuzzRequest(Arrays.asList(1, 5, -2)))).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private HttpStatus postForStatusCode(FizzBuzzRequest request) {
        return restTemplate.postForEntity(getUrl(), request, FizzBuzzResponse.class).getStatusCode();
    }

    private String getUrl() {
        return "http://localhost:" + port + "/fizzbuzz";
    }
}
