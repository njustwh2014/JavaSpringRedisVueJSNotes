# Redis主从配置
参考：https://www.jianshu.com/p/fe7c2e1310c9
## 启动主节点
```
# 停止容器
docker stop redis-master redis-slave redis-slave-2
# 删除容器
docker container rm redis-master redis-slave redis-slave-2
# 运行服务
docker run -it --name redis-master -v F:/codeHub/docker/redismaster/redis.conf:/usr/local/etc/redis/redis.conf -v F:/codeHub/docker/redismaster/data:/data -d -p 6300:6379 redis /bin/bash
# 进入容器
docker exec -it redis-master bash
# 加载配置
redis-server /usr/local/etc/redis/redis.conf
# 测试连接
redis-cli -a wanghuan
```

解决重启后连不上redis问题
```
# 进入容器
docker exec -it redis-master bash
# 加载配置
redis-server /usr/local/etc/redis/redis.conf
# 测试连接
redis-cli -a wanghuan
```
## 启动从节点
```
# 运行服务
docker run -it --name redis-slave -v F:/codeHub/docker/redisslave/redis.conf:/usr/local/etc/redis/redis.conf -d -p 6301:6379 redis /bin/bash
# 进入容器
docker exec -it redis-slave bash
# 加载配置
redis-server /usr/local/etc/redis/redis.conf
# 测试连接
redis-cli
# 密码认证
auth <slave-password>
```
### 从节点2
```
docker run -it --name redis-slave-2 -v F:/codeHub/docker/redisslave02/redis.conf:/usr/local/etc/redis/redis.conf -d -p 6302:6379 redis /bin/bash
# 进入容器
docker exec -it redis-slave-2 bash
# 加载配置
redis-server /usr/local/etc/redis/redis.conf
# 测试连接
redis-cli
# 密码认证
auth <slave-password>
```
## 查看容器ip地址
```docker
docker inspect containerid
docker inspect redismaster
```
## 进入docker容器内部，查看当前redis角色
```bash
docker exec -it redisslave redis-cli
```
```redis
info replication
SLAVEOF host port
SLAVEOF 172.17.0.3 6379
```
## 查看从机
```docker
docker exec -it redismaster redis-cli
```
```redis
info replication
```