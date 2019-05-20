package cn.edu.seu.wh.redis;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V>  extends LinkedHashMap<K,V> {
    private final int MAX_CACHE_SIZE;

    public LRUCache(int initialCapacity) {
        // +1为了防止当到达hashmap的capacity时hashmap扩容
        super((int)Math.ceil(initialCapacity/0.75)+1,0.75f,true);
        this.MAX_CACHE_SIZE = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(Map.Entry<K,V> entry:entrySet()){
            sb.append(String.format("%s:%s",entry.getKey(),entry.getValue()));
        }
        return sb.toString();
    }
}

class Test{
    
    public static void main(String[] args){  
        Map<Integer,Integer> map=new LRUCache<Integer, Integer>(4);
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        map.put(4,5);
        for(Iterator<Map.Entry<Integer,Integer>> it=map.entrySet().iterator();it.hasNext();){
            System.out.println(it.next().toString());
        }
        System.out.println("===============================================");
        map.get(3);
        for(Iterator<Map.Entry<Integer,Integer>> it=map.entrySet().iterator();it.hasNext();){
            System.out.println(it.next().toString());
        }
        System.out.println("map.put(5,6)");
        map.put(5,6);
        for(Iterator<Map.Entry<Integer,Integer>> it=map.entrySet().iterator();it.hasNext();){
            System.out.println(it.next().toString());
        }
        /*
         * 优雅写法，不用新建类
         * */
        final int cachesize=5;
        Map<String,String> map2=new LinkedHashMap<String, String>((int)Math.ceil(cachesize/0.75+1),0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size()>cachesize;
            }
        };
        System.out.println("=======================================");
        map2.put("wanghuan","cool");
        map2.put("james","cool");
        for(Iterator<Map.Entry<String,String>> it=map2.entrySet().iterator();it.hasNext();){
            System.out.println(it.next().toString());
        }
    }



}