FROM hub.c.163.com/library/java:8-alpine
FROM hub.c.163.com/library/maven:3

MAINTAINER yangxinxin

COPY / /client

WORKDIR /client

RUN mvn clean package install -Dmaven.test.skip=true

EXPOSE 8080

WORKDIR /client/target

RUN mv *.jar client.jar

ENTRYPOINT ["java","-jar","/client.jar"]