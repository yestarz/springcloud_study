FROM hub.c.163.com/library/java:8-alpine

MAINTAINER yangxinxin

CMD ["build.sh"]

ADD /target/*.jar client-test.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/client-test.jar"]