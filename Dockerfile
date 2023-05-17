FROM openjdk:17-jdk-alpine3.14
COPY "./target/music-catalog.jar" "/app/music-catalog.jar"
EXPOSE 8080
CMD [ "java", "-jar", "/app/music-catalog.jar" ]