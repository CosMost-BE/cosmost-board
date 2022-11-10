FROM openjdk:17-ea-11-jdk-slim
EXPOSE 9005
ENV TZ Asia/Seoul
COPY build/libs/cosmost-board-1.0.jar ./BoardService.jar
ENTRYPOINT ["java", "-jar", "BoardService.jar"]


