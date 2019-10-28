## 遇到的问题

1. 调用product服务报classNotFound

解决办法：
order 的pom.xml 加：

```xml
   <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
    <version>2.0.0.M3</version>
   </dependency>
```