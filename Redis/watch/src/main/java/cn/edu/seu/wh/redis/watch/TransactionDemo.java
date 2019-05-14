package cn.edu.seu.wh.redis.watch;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class TransactionDemo {

    public static void main(String[] args){
        Jedis jedis=new Jedis("localhost",6300);
        jedis.auth("wanghuan");
        String userId="abc";
        String key=keyFor(userId);
        jedis.setnx(key,String.valueOf(5));//setnx做初始化
        System.out.println(doubleAccount(jedis,userId));
        jedis.close();


    }

    // watch 是一种乐观锁
    public static int doubleAccount(Jedis jedis,String userId){
        String key=keyFor(userId);
        while(true){
            jedis.watch(key);
            int value=Integer.parseInt(jedis.get(key));
            value*=2;
            Transaction tx=jedis.multi();
            tx.set(key,String.valueOf(value));
            List<Object> res=tx.exec();
            if(res!=null){
                break;//success
            }
        }
        return Integer.parseInt(jedis.get(key));//重新获取余额
    }

    public static String keyFor(String userId){
        return String.format("account_%s",userId);
    }
}
