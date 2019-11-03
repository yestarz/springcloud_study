## 通过api网关访问微服务其他的服务的接口

1. 启动类添加注解 `@EnableZuulProxy`

2. 启动product服务

3. 先用普通的访问product的/product/list 接口 ，发现可以访问成功

4. 用网关的地址 如 http://localhost:9000/${servername}/url 访问  就是 http://localhost:9000/product/product/list

## 自定义访问的路径

1. 在配置文件中加入配置：


```yml

zuul:
  routes:
    myProduct: 
      ## 路由的url
      path: /myProduct1/** 
      ## 要路由的服务名字
      serviceId: product

```

> 也有一个简洁的写法:


```yml

zuul:
  routes:
    product: /myProduct1

```

## 查看所有的路由信息：

1. 配置 

```yml
management:
  security:
    enabled: false
```

2. 访问 http://localhost:9000/application/routes

## 配置部分地址不可用过网关访问：

```yml
zuul:
  routes:
    myProduct:
      path: /myProduct1/**
      serviceId: product
    product: /myProduct2/**
  ## 主要是下面这部分代码：
  ignored-patterns:
    - /product/product/listForProductId
    - /myProduct1/product/listForProductId

```

> 也可以写成通配符的方式：

```yml
zuul:
  routes:
    myProduct:
      path: /myProduct1/**
      serviceId: product
    product: /myProduct2/**
  ## 主要是下面这部分代码：
  ignored-patterns:
    - /**/product/listForProductId
```

## 通过设置sensitiveHeaders 可以使得传递cookie:

```yml
zuul:
  routes:
    myProduct:
      path: /myProduct1/**
      serviceId: product
      ##  主要是下面这部分代码,设置为null
      sensitiveHeaders:
```


## 忽略全部服务的敏感头：
```yaml
zuul.sensitive-headers= 
```