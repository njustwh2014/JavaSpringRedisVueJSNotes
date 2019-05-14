package cn.edu.seu.wh.redis.code;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class CodeApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(CodeApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Jedis jedis=new Jedis("localhost",6300);
		jedis.auth("wanghuan");
		for(int i=0;i<20000;i++){
			String key = String.format("key%d",i);
			String value=String.valueOf(i);
			jedis.set(key,value);
		}
	}
}
