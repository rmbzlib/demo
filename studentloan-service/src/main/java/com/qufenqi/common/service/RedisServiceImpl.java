package com.qufenqi.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangyang on 2016/8/19.
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    //操作redis客户端
    private static Jedis jedis;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    /**
     * 通过key删除（字节）
     *
     * @param key
     */
    public void del(byte[] key) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.del(key);
        }catch (Exception e){
            logger.error("",e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 通过key删除
     *
     * @param key
     */
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.del(key);
        }catch (Exception e){
            logger.error("del key"+key,e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 添加key value 并且设置存活时间(byte)
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(byte[] key, byte[] value, int liveTime) {
        this.set(key, value);
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.expire(key, liveTime);
        }catch (Exception e){
            logger.error("",e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, int liveTime) {
        this.set(key, value);
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.expire(key, liveTime);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.set(key, value);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.set(key, value);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        String a = "";
        try {
            jedis= jedisPool.getResource();
            a = jedis.get(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 获取redis value (byte [] )(反序列化)
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        Jedis jedis = null;
        byte[]  a = null;
        try {
            jedis= jedisPool.getResource();
            a = jedis.get(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 通过正则匹配keys
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        Set<String> a = null;
        try {
            jedis= jedisPool.getResource();
            a = jedis.keys(pattern);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        Jedis jedis = null;
        boolean a = false;
        try {
            jedis= jedisPool.getResource();
            a = jedis.exists(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 清空redis 所有数据
     *
     * @return
     */
    public String flushDB() {
        Jedis jedis = null;
        String a = "";
        try {
            jedis= jedisPool.getResource();
            a = jedis.flushDB();
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 查看redis里有多少数据
     */
    public long dbSize() {

//        return this.getJedis().dbSize();
        Jedis jedis = null;
        long a = 0;
        try {
            jedis= jedisPool.getResource();
            a = jedis.dbSize();
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 检查是否连接成功
     *
     * @return
     */
    public String ping() {
        Jedis jedis = null;
        String a = "";
        try {
            jedis= jedisPool.getResource();
            a = jedis.ping();
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 获取一个jedis 客户端
     *
     * @return
     */
    private Jedis getJedis() {
        if (jedis == null) {
//            jedis = jedisConnectionFactory.getShardInfo().createResource();
//            return jedis;
            return jedisConnectionFactory.getShardInfo().createResource();
        }
        return jedis;
    }
    /**
     * 获取一个jedis 客户端
     *
     * @return
     */
    @Autowired
    protected JedisPool jedisPool;

    /**
     * 释放jedis资源
     * @param jedis
     */
    public void release(Jedis jedis, boolean isBroken) {
        if (jedis != null) {
            if (isBroken) {
                jedisPool.returnBrokenResource(jedis);
            } else {
                jedisPool.returnResource(jedis);
            }
        }
    }
    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
    public void setList(String key, String... value){
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.rpush(key, value);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }
    /**
     * 减用户的摇奖次数
     *
     * @param key
     */
    public long decr(String key){
        Jedis jedis = null;
        long re = 0;
        try {
            jedis= jedisPool.getResource();
            re = jedis.decr(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return re;
    }

    @Override
    public void zadd(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.zadd(key, score, member);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    @Override
    public void zrem(String key, String member) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.zrem(key,member);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    @Override
    public Set zrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            return jedis.zrange(key,start,end);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return new HashSet();
    }

    /**
     * 通过key删除
     *
     * @param key
     */
    public String delList(String key) {
        Jedis jedis = null;
        String redis = "";
        try {
            jedis= jedisPool.getResource();
//            System.out.print("!!!!!!!!!!!!!!!!!!"+key);
            redis = jedis.lpop(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return redis;
    }

    /**
     * 通过key 修改 第几个要素
     *
     * @param key
     */
    public String setListSub(String key, String value) {
        Jedis jedis = null;
        String redis = "";
        try {
            jedis= jedisPool.getResource();
            redis = jedis.lset(key, Long.valueOf(value), "0.00000");
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return "";
    }

    /**
     * 通过key获取
     *
     * @param key
     */
    public List<String> getList(String key){
        Jedis jedis = null;
        boolean isBroken = false;
        List<String> lists = null;
        try {
            jedis= jedisPool.getResource();
            lists =  jedis.lrange(key, 0, -1);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return lists;
    }

    /**
     * 通过key获取
     *
     * @param key
     */
    public List<String> getListByPage(String key, int start, int end){
        Jedis jedis = null;
        boolean isBroken = false;
        List<String> lists = null;
        try {
            jedis= jedisPool.getResource();
            lists =  jedis.lrange(key,start,end);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return lists;
    }
    /**
     * 通过key获取
     *
     * @param key
     */
    public List<String> getListSize(String key){
        Jedis jedis = null;
        List<String> listString =  null;
        boolean isBroken = false;
        long listSize = 0;
        try {
            if (jedis==null){
                jedis= jedisPool.getResource();
            }
            listString = new ArrayList<String>();
            for (int i = 1; i < 15; i++) {
                listSize = jedis.llen(String.valueOf(i));
                listString.add(String.valueOf(listSize));
            }
        }catch (Exception e){
            logger.error("",e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return listString;
    }
}
