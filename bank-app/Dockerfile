FROM openjdk:11.0-slim
ARG JAR_FILE=build/libs/bank-app-1.0.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar"]
