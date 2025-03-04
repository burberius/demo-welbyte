FROM amazoncorretto:23-alpine
ARG JAR_FILE=build/libs/*[^plain].jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]