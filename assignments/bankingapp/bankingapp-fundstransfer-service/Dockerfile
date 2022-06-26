FROM openjdk:11
LABEL version="1.0"
LABEL description="Fundstransfer Service Image"
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8093