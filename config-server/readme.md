## 提示

如果git是私有仓库则需要填写用户名和密码，公有仓库可以不用填写


## 关于eureka在外网中部署的一点坑

1. 首先我的eureka是在外网的docker容器中启动的，所以首先，我要在config中配置正确的eureka地址
2. 加入以下的配置：

```yml
instance:
    prefer-ip-address: true
    ip-address: 116.196.118.178
    non-secure-port: ${server.port}  ## 这里的port是config的端口
```

3. config客户端也需要填写正确的eureka地址