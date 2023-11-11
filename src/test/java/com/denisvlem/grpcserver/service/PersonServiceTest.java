package com.denisvlem.grpcserver.service;

import com.denisvlem.grpc.person.PersonRequest;
import com.denisvlem.grpc.person.PersonServiceGrpc;
import com.denisvlem.grpcserver.GrpcTestConfiguration;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("grpc-test")
@SpringBootTest
@DirtiesContext
@SpringJUnitConfig(classes = GrpcTestConfiguration.class)
class PersonServiceTest {

    @GrpcClient("inProcess")
    private PersonServiceGrpc.PersonServiceBlockingStub service;

    @Test
    void givenUserId_whenGetUser_shouldReturnOne() {
        var personRequest = PersonRequest.newBuilder()
            .setUserId(0).build();

        var response = service.getPerson(personRequest);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull().isEqualTo("4f2ed743-1ae3-4798-b160-e835ee9b7291");
    }
}