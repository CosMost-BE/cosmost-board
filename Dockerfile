#FROM openjdk:17-ea-11-jdk-slim
#VOLUME /tmp
#COPY build/libs/cosmost-board-1.0.jar BoardService.jar
#ENTRYPOINT ["java", "jar", "BoardService.jar"]

#COPY "test jenkins"

FROM openjdk:17-ea-11-jdk-slim

RUN chmod 755 gradlew
RUN ./gradlew build -x test

COPY build/libs/cosmost-board-1.0.jar BoardService.jar
ENV TZ Asia/Seoul

EXPOSE 9005
ENTRYPOINT ["java", "-jar", "BoardService.jar"]
VOLUME /tmp



