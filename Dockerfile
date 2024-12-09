FROM openjdk:11-jdk-slim

WORKDIR /app

COPY ./sspr_5/TestAsServer /app/TestAsServer
COPY ./sspr_5/NewAsClient /app/NewAsClient

RUN javac /app/TestAsServer/src/Main.java -d /app/TestAsServer/src
RUN javac /app/NewAsClient/src/Main.java -d /app/NewAsClient/src


CMD ["java", "-cp", "/app/TestAsServer/src", "Main"]