FROM openjdk:11
COPY ./target/EventProject-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar","web -webAllowOthers -tcp -tcpAllowOthers -browser"]