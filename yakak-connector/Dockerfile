# Stage 1: Build
FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:jdk-21 AS build
COPY --chown=quarkus:quarkus /yakak-connector /code
USER quarkus
WORKDIR /code
RUN chmod +x ./mvnw && \
    ./mvnw -B org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline && \
    ./mvnw package -Dnative
# Cleanup unnecessary files
RUN rm -rf /code

# Stage 2: Create the final image
FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /work/
COPY --from=build /code/target/*-runner /work/application
# Combine permission-related commands
RUN chmod -R 775 /work && \
    chown -R 1001:root /work && \
    chmod -R "g+rwX" /work

EXPOSE 8080
USER 1001
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
