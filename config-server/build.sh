mvn clean package -Dmaven.test.skip=true
docker build -t registry.cn-hangzhou.aliyuncs.com/springcloud_study/config-server .
docker push registry.cn-hangzhou.aliyuncs.com/springcloud_study/config-server