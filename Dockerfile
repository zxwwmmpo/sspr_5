FROM openjdk:11-jdk-slim

WORKDIR /app

COPY TestAsServer/ ./TestAsServer/
COPY NewAsClient/ ./NewAsClient/

RUN javac TestAsServer/src/*.java

CMD ["java", "-cp", "TestAsServer/src", "TestAsServer.Main"]