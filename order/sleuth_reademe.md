1. 增加依赖
```xml
 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-sleuth</artifactId>
        </dependency>
```


2. 添加zipkin依赖

```xml
   <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-sleuth-zipkin</artifactId>
        </dependency>
```

3. 服务器docker安装zipkin：


https://zipkin.io

```
docker run -d -p 9411:9411 openzipkin/zipkin
```
访问：http://116.196.118.178:9411/zipkin/

4. 注意：

```xml
     <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```

上面这个依赖已经包含了 spring-cloud-starter-sleuth 和 spring-cloud-starter-sleuth-zipkin，所以可以只用这一个依赖

5. 添加配置：

```yml
spring.zipkin.base-url= http://116.196.118.178:9411

spring: 
  sleuth:
    sampler:
      percentage: 1 这个是百分比

```


如果发现配置正确了还是在zipkin的控制台不显示 那么配置：

 zipkin:
    sender:
      type: web
