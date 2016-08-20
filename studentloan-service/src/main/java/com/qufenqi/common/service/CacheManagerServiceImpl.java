package com.qufenqi.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangyang on 2016/8/20.
 */
public class CacheManagerServiceImpl implements CacheManagerService{
    private static Logger logger = LoggerFactory.getLogger(CacheManagerServiceImpl.class);

    private boolean isHaveRedisServer=true;

    @Autowired
    RedisService redisService;

    private static StringRedisSerializer stringRedisSerializer;

    private static ConcurrentHashMap<String,Object> memoryCache = new ConcurrentHashMap<String,Object>();
    

    @PostConstruct
    public void init() {
        isHaveRedisServer=redisService.checkExistServer();

        stringRedisSerializer=new StringRedisSerializer();
    }

    @Override
    public void del(byte[] key) {
        if(!isHaveRedisServer){
            //ignore delete by byte[]
        }
        else{
            redisService.del(key);
        }
    }

    @Override
    public void del(String key) {
        if(!isHaveRedisServer){
            if(memoryCache.containsKey(key))
                memoryCache.remove(key);
        }
        else{
            redisService.del(key);
        }
    }

    @Override
    public void set(byte[] key, byte[] value, int liveTime) {
        if(!isHaveRedisServer){
            this.del(key);

            //ignore byte
        }
        else{
            redisService.set(key, value, liveTime);
        }
    }

    @Override
    public void set(String key, String value, int liveTime) {
        if(!isHaveRedisServer){
            if(value == null){
                return;
            }
            this.set(key, value);
        }
        else{
            redisService.set(key, value, liveTime);
        }
    }

    @Override
    public void set(String key, String value) {
        if(!isHaveRedisServer){
            if(value == null){
                return;
            }
            memoryCache.put(key, value);
        }
        else{
            redisService.set(key, value);
        }
    }

    @Override
    public void set(byte[] key, byte[] value) {
        if(!isHaveRedisServer){
            //ignore
        }
        else{
            redisService.set(key, value);
        }
    }

    @Override
    public String get(String key) {
        if (!isHaveRedisServer) {
            if (memoryCache.containsKey(key))
                return (String) memoryCache.get(key);

            return null;
        }

        return redisService.get(key);
    }

    @Override
    public byte[] get(byte[] key) {
        if(!isHaveRedisServer){
            return null;
        }
        return redisService.get(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        if(!isHaveRedisServer){
            return null;
        }
        return redisService.keys(pattern);
    }

    @Override
    public boolean exists(String key) {
        if(!isHaveRedisServer){
            return memoryCache.containsKey(key);
        }
        return redisService.exists(key);
    }

    @Override
    public String clear() {
        if(!isHaveRedisServer){
            memoryCache.clear();
            return "1";
        }
        return redisService.flushDB();
    }

    @Override
    public long size() {

        if(!isHaveRedisServer){
            return memoryCache.size();
        }
        return redisService.dbSize();
    }

    @Override
    public void setObject(String key, Object value){
        if(!isHaveRedisServer){
            if(value == null){
                return;
            }
            memoryCache.put(key, value);
        }
        else{
            try {
                byte[] bv = serialize(value);
                byte[] bk = stringRedisSerializer.serialize(key);

                set(bk, bv);
            }
            catch(Exception ex){
                logger.error("Error ser", ex);
            }
        }
    }

    @Override
    public void setObject(String key, Object value, int liveTime){
        if(!isHaveRedisServer){
            if(value == null){
                return;
            }
            memoryCache.put(key, value);
        }
        else{
            try {
                byte[] bv = serialize(value);
//                byte[] bk = serialize(key);
                byte[] bk = stringRedisSerializer.serialize(key);

                set(bk, bv, liveTime);
            }
            catch(Exception ex){
                logger.error("Error ser", ex);
            }
        }
    }

    @Override
    public Object getObject(String key){
        if(!isHaveRedisServer){
            if(memoryCache.containsKey(key))
                return memoryCache.get(key);
            return null;
        }
        try {
//            byte[] bk = serialize(key);
            byte[] bk = stringRedisSerializer.serialize(key);
            byte[] b = get(bk);
            if(b==null)
                return null;
            return deserialize(b);
        }
        catch(Exception ex){
            logger.error("Error ser", ex);
        }
        return null;
    }

    @Override
    public String delList(String key) {
        return redisService.delList(key);
    }

    @Override
    public List<String> getList(String key) {
            return redisService.getList(key);
    }

    @Override
    public List<String> getListByPage(String key, int start, int end) {
        return redisService.getListByPage(key,start,end);
    }

    private byte[] serialize(Object o) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); //构造一个字节输出流
        ObjectOutputStream oos = new ObjectOutputStream(baos); //构造一个类输出流
        //oos.writeObject(list); //写这个对象
        oos.writeObject(o); //写这个对象
        byte[] buf = baos.toByteArray(); //从这个地层字节流中把传输的数组给一个新的数组
        oos.flush();
        return buf;
    }

    private Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        //构建一个类输入流，用字节输入流实现
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        return obj;
    }

}
