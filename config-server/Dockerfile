FROM hub.c.163.com/library/openjdk-8-alpine

MAINTAINER yangxin

ADD /target/*.jar config-server.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","/config-server.jar"]