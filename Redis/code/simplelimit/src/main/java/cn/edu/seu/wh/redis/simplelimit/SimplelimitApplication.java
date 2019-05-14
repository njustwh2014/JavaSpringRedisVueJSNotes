package cn.edu.seu.wh.redis.simplelimit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class SimplelimitApplication implements ApplicationRunner {
    @Autowired
    SimpleRateLimiter simpleRateLimiter;
	public static void main(String[] args) {
		SpringApplication.run(SimplelimitApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Jedis jedis=new Jedis("localhost",6300);
        jedis.auth("wanghuan");
		simpleRateLimiter.setJedis(jedis);
		for(int i=0;i<20;i++){
		    System.out.println(simpleRateLimiter.isActionAllowed("wanghuan","reply",60,5));
        }
        System.out.println("wanghuan");
	}
}
