FROM maven

WORKDIR /reconciliation-service
COPY pom.xml .
COPY src src
RUN mvn package -DskipTests
ENTRYPOINT ["java","-jar","target/reconciliation-service-0.0.1-SNAPSHOT.jar"]
