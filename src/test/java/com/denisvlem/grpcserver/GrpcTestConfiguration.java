package com.denisvlem.grpcserver;

import com.denisvlem.grpcserver.service.PersonService;
import net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration;
import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration;
import net.devh.boot.grpc.server.autoconfigure.GrpcServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("grpc-test")
@Configuration
@ImportAutoConfiguration({
    GrpcServerAutoConfiguration.class, // Create required server beans
    GrpcServerFactoryAutoConfiguration.class, // Select server implementation
    GrpcClientAutoConfiguration.class})
public class GrpcTestConfiguration {

    @Bean
    public PersonService personService() {
        return new PersonService();
    }
}
