package org.wachowiak.docker.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.wachowiak.docker.Application;

import static org.junit.Assert.assertArrayEquals;

@SpringJUnitWebConfig
@SpringBootTest(classes = Application.class)
public class MathHandlerTest {

    private RouterFunction<ServerResponse> routerFunction;

    @BeforeEach
    void setupClient(@Autowired RouterFunction<ServerResponse> routerFunction) {
        this.routerFunction = routerFunction;
    }


    @Test
    public void addTwoDigitsReturnCorrectResult() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(routerFunction)
                .build();
        ResponseSpec responseSpec = client.get().uri("/math/add?a={0}&b={1}", 5, 3).exchange();
        verifyStatus(responseSpec, HttpStatus.OK);
        verifyContent(responseSpec, 8);
    }

    @Test
    public void subTwoDigitsReturnCorrectResult() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(routerFunction)
                .build();
        ResponseSpec responseSpec = client.get().uri("/math/sub?a={0}&b={1}", 2, 3).exchange();
        verifyStatus(responseSpec, HttpStatus.OK);
        verifyContent(responseSpec, -1);
    }

    @Test
    public void mulTwoDigitsReturnCorrectResult() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(routerFunction)
                .build();
        ResponseSpec responseSpec = client.get().uri("/math/mul?a={0}&b={1}", 2, 3).exchange();
        verifyStatus(responseSpec, HttpStatus.OK);
        verifyContent(responseSpec, 6);
    }

    @Test
    public void divTwoDigitsReturnCorrectResult() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(routerFunction)
                .build();
        ResponseSpec responseSpec = client.get().uri("/math/div?a={0}&b={1}", 2, 3).exchange();
        verifyStatus(responseSpec, HttpStatus.OK);
        verifyContent(responseSpec, 0);
    }

    @Test
    public void divByZeroReturnBadRequest() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(routerFunction)
                .build();
        ResponseSpec responseSpec = client.get().uri("/math/div?a={0}&b={1}", 2, 0).exchange();
        verifyStatus(responseSpec, HttpStatus.BAD_REQUEST);
    }

    private void verifyContent(ResponseSpec responseSpec, long result) {
        verifyContent(responseSpec, String.valueOf(result));
    }

    private void verifyContent(ResponseSpec responseSpec, String data) {
        assertArrayEquals(responseSpec.expectBody().returnResult().getResponseBody(), data.getBytes());
    }

    private void verifyStatus(ResponseSpec responseSpec, HttpStatus httpStatus) {
        responseSpec.expectStatus().isEqualTo(httpStatus);
    }
}