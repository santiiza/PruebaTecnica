FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY build/libs/cde-msa-bp-async-customer-0.0.1.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]