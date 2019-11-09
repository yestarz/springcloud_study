FROM hub.c.163.com/library/java:8-alpine

MAINTAINER yangxin

ADD /target/*.jar config-server.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/config-server.jar"]