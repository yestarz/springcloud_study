FROM hub.c.163.com/library/java:8-alpine
FROM hub.c.163.com/library/maven:3

MAINTAINER yangxinxin

RUN mvn clean package install -Dmaven.test.skip=true

ADD /target/*.jar client-test.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/client-test.jar"]