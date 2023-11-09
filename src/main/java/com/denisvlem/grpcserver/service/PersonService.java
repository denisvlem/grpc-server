package com.denisvlem.grpcserver.service;

import com.denisvlem.grpc.person.PersonRequest;
import com.denisvlem.grpc.person.PersonResponse;
import com.denisvlem.grpc.person.PersonServiceGrpc;
import com.denisvlem.grpc.person.Sex;
import com.denisvlem.grpcserver.exception.PersonException;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;

@GrpcService
public class PersonService extends PersonServiceGrpc.PersonServiceImplBase {

    @Override
    public void getPerson(PersonRequest request, StreamObserver<PersonResponse> responseStreamObserver) {
        var personDb = getPersonDb();
        try {
            var personResponse = personDb.get(request.getUserId());
            responseStreamObserver.onNext(personResponse);
            responseStreamObserver.onCompleted();
        } catch (Exception e) {
            throw new PersonException(String.format("Не удалось получить пользователя, причина: %s", e.getMessage()));
        }
    }

    private static List<PersonResponse> getPersonDb() {
        return Stream.of(
            PersonResponse.newBuilder()
                .setId("4f2ed743-1ae3-4798-b160-e835ee9b7291")
                .setName("User 1")
                .setBirthDate(Timestamp.newBuilder().setSeconds(OffsetDateTime.now().toEpochSecond()).build())
                .setSex(Sex.MALE)
                .build(),
            PersonResponse.newBuilder()
                .setId("4f2ed743-1ae3-4798-b160-e835ee9b7292")
                .setName("User 2")
                .setBirthDate(Timestamp.newBuilder().setSeconds(OffsetDateTime.now().toEpochSecond()).build())
                .setSex(Sex.FEMALE)
                .build()
        ).toList();
    }

}
