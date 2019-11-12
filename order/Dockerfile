FROM hub.c.163.com/library/java:8-alpine

MAINTAINER yangxin

ADD /order-server/target/*.jar order.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/product.jar"]