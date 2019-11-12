mvn clean package install -Dmaven.test.skip=true
docker build -t registry.cn-hangzhou.aliyuncs.com/springcloud_study/order .
docker push registry.cn-hangzhou.aliyuncs.com/springcloud_study/order