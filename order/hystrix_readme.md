## 

1. 引入依赖

```xml
    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
```

2. 启动类上加注解 `@EnableCircuitBreaker` 

> 降级也并非只是调用其他服务才会触发，如果本服务也抛出了异常，那么也会触发降级

> @DefaultProperties(defaultFallback = "defaultFallback") 可以注解在类上，该类的方法触发降级都可以使用这个方法


超时时间的配置：com.netflix.hystrix.HystrixCommandProperties 这个类有这个配置的名字

```
@HystrixCommand(commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
});
```


可以通过配置文件来写超时时间：

```yml
hystrix:
  command:
    ## 这里default表示的是全局的配置
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 1000
```

通过commandKey为某个方法来设置超时时间，默认的key的值就是方法名字


```yml
hystrix:
  command:
    ## 这里getProductListYml表示的是方法名字，也可以在HystrixCommand注解里面设置
    getProductListYml:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 1000
```


## 通过feign来开发hystrix配置

> 由于feign自己就引入了hystrix的包，所以无须再引入

再配置文件中写以下配置：

```yml
feign:
  hystrix:
    enabled: true
```


> 这里记录一个坑，编写完ProductApi的降级类以后，在order这边启动项目，报了下面的错：


```
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'requestMappingHandlerMapping' defined in class path resource [org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration$EnableWebMvcConfiguration.class]: Invocation of init method failed; nested exception is java.lang.IllegalStateException: Ambiguous mapping. Cannot map 'link.yangxin.product.api.ProductApi' method 
public abstract link.yangxin.my.commons.R<java.util.List<link.yangxin.product.common.vo.ProductInfoVO>> link.yangxin.product.api.ProductApi.listForProductId(java.util.List<java.lang.String>)
to {[/product/listForProductId],methods=[POST]}: There is already 'productApi.ProductApiFallBack' bean method
public link.yangxin.my.commons.R<java.util.List<link.yangxin.product.common.vo.ProductInfoVO>> link.yangxin.product.api.ProductApi$ProductApiFallBack.listForProductId(java.util.List<java.lang.String>) mapped.
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1716) ~[spring-beans-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:582) ~[spring-beans-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:499) ~[spring-beans-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:312) ~[spring-beans-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:310) ~[spring-beans-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:755) ~[spring-beans-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:868) ~[spring-context-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:549) ~[spring-context-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:122) ~[spring-boot-2.0.0.M3.jar:2.0.0.M3]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:750) [spring-boot-2.0.0.M3.jar:2.0.0.M3]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:386) [spring-boot-2.0.0.M3.jar:2.0.0.M3]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:327) [spring-boot-2.0.0.M3.jar:2.0.0.M3]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1245) [spring-boot-2.0.0.M3.jar:2.0.0.M3]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1233) [spring-boot-2.0.0.M3.jar:2.0.0.M3]
	at link.yangxin.order.OrderApplication.main(OrderApplication.java:22) [classes/:na]
Caused by: java.lang.IllegalStateException: Ambiguous mapping. Cannot map 'link.yangxin.product.api.ProductApi' method 
public abstract link.yangxin.my.commons.R<java.util.List<link.yangxin.product.common.vo.ProductInfoVO>> link.yangxin.product.api.ProductApi.listForProductId(java.util.List<java.lang.String>)
to {[/product/listForProductId],methods=[POST]}: There is already 'productApi.ProductApiFallBack' bean method
public link.yangxin.my.commons.R<java.util.List<link.yangxin.product.common.vo.ProductInfoVO>> link.yangxin.product.api.ProductApi$ProductApiFallBack.listForProductId(java.util.List<java.lang.String>) mapped.
	at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping$MappingRegistry.assertUniqueMethodMapping(AbstractHandlerMethodMapping.java:582) ~[spring-webmvc-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping$MappingRegistry.register(AbstractHandlerMethodMapping.java:546) ~[spring-webmvc-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.registerHandlerMethod(AbstractHandlerMethodMapping.java:266) ~[spring-webmvc-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.detectHandlerMethods(AbstractHandlerMethodMapping.java:251) ~[spring-webmvc-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.initHandlerMethods(AbstractHandlerMethodMapping.java:218) ~[spring-webmvc-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.afterPropertiesSet(AbstractHandlerMethodMapping.java:188) ~[spring-webmvc-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping.afterPropertiesSet(RequestMappingHandlerMapping.java:129) ~[spring-webmvc-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1776) ~[spring-beans-5.0.0.RC3.jar:5.0.0.RC3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1712) ~[spring-beans-5.0.0.RC3.jar:5.0.0.RC3]
	... 16 common frames omitted
```

查了下资料，是因为我在ProductApi类上加了RequestMapping注解。修改以下就可以了，去掉了RequestMapping注解就可以了。



## 可视化组件：

1. 添加依赖：
```xml
 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
        </dependency>
        
        <!--如果没有引入actuator 需要引入-->
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```
2. 启动类加上注解：@EnableHystrixDashboard

3. 启动成功后，可以访问：http://localhost:8081/hystrix

4. 输入：http://localhost:8081/hystrix.stream  ，发现提示了Unable to connect to Command Metric Stream.

5. 在配置文件中添加以下的配置：

```yml
management:
  context-path: /
```
 
 
 
 ### Zuul服务网关也可以通过hystrix的配置来配置超时时间：
 
 ```yml
  hystrix:
    command:
      default:
        execution:
          isolation:
            thread:
              timeoutInMilliseconds: 5000
 ```


