package com.denisvlem.grpcserver.exception;

import com.denisvlem.grpc.person.PersonErrorResponse;
import com.google.protobuf.Any;
import com.google.protobuf.Timestamp;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class PersonExceptionHandler {

    @GrpcExceptionHandler(PersonException.class)
    public StatusRuntimeException handlePersonException(PersonException personException) {

        var personResponse = PersonErrorResponse.newBuilder()
            .setTimeStamp(Timestamp.newBuilder().setSeconds(personException.getRaisingTime().getEpochSecond())
                .setNanos(personException.getRaisingTime().getNano())
                .build())
            .setMessage(personException.getMessage())
            .build();

        Status status = Status.newBuilder()
            .setCode(Code.INVALID_ARGUMENT.getNumber())
            .setMessage(personException.getMessage())
            .addDetails(Any.pack(personResponse))
            .build();

        return StatusProto.toStatusRuntimeException(status);
    }
}
