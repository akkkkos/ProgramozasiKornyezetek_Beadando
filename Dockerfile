FROM openjdk:17-jdk-alpine3.14
COPY "./target/tvshowlist.jar" "/app/tvshowlist.jar"
EXPOSE 8080
CMD [ "java", "-jar", "/app/tvshowlist.jar" ]