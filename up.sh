# Works only with Java 8 because of javax.annotation.Generated used by grpc generator, so jdk installation needs to be specified explicitly
./gradlew clean build -Dorg.gradle.java.home=/usr/lib/jvm/java-8-openjdk-amd64
docker-compose up
