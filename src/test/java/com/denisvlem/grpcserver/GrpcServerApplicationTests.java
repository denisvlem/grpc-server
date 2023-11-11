package com.denisvlem.grpcserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("default")
@SpringBootTest
class GrpcServerApplicationTests {

    @Autowired
    private GrpcServerApplication application;

    @Test
    void contextLoads() {
        assertThat(application).isNotNull();
    }

}
