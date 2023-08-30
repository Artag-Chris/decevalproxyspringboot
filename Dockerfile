FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
COPY ./target/original-deceval-0.0.1-SNAPSHOT.jar app.jar
COPY ./keys/SSLClientAutoGen.jks /SSLClientAutoGen.jks
COPY ./keys/WSSecurityStore.jks /WSSecurityStore.jks
COPY ./src/main/resources/application-docker.properties client.properties
ENV  PATH_CLIENT_PROPERTIES client.properties
EXPOSE 9000
ENTRYPOINT ["java","-jar","/app.jar"]