docker run -p 6382:6379 --name redismaster -v F:/codeHub/docker/redis/redis.conf:/etc/redis/redis.conf -v F:/codeHub/docker/redis/data:/data -d redis redis-server /etc/redis/redis.conf 

