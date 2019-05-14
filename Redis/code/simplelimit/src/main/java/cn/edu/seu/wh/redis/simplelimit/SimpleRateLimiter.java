package cn.edu.seu.wh.redis.simplelimit;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/*
* redis实现简单的限流
*
* */
@Component
public class SimpleRateLimiter {
    private Jedis jedis;

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
    public boolean isActionAllowed(String userId,String actionKey,int period,int maxCount){
        String key = String.format("hist:%s:%s",userId,actionKey);
        long nowTs=System.currentTimeMillis();
        Pipeline pipe=jedis.pipelined();
        pipe.multi();//事务开始
        pipe.zadd(key,nowTs,""+nowTs);
        pipe.zremrangeByScore(key,0,nowTs-period*1000);
        Response<Long> count=pipe.zcard(key);
        pipe.expire(key,period+1);
        pipe.exec();//事务执行
        pipe.close();
        return count.get()<=maxCount;
    }
}
