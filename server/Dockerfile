FROM openjdk:8

WORKDIR /usr/lib/grpc-server
COPY build/libs/*.jar ./

RUN java -jar server-0.0.1-SNAPSHOT.jar