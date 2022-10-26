#FROM openjdk:17-ea-11-jdk-slim
#VOLUME /tmp
#COPY build/libs/cosmost-board-1.0.jar BoardService.jar
#ENTRYPOINT ["java", "jar", "BoardService.jar"]

FROM openjdk:17-ea-11-jdk-slim

COPY build/libs/cosmost-board-1.0.jar BoardService.jar
ENV TZ Asia/Seoul

COPY chmod755 gradlew
RUN ./gradlew build -x test
EXPOSE 9005
ENTRYPOINT ["java", "jar", "BoardService.jar"]



