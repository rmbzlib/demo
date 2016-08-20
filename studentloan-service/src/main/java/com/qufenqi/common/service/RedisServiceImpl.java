package com.qufenqi.common.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnection;
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

    private Jedis jedis;

    private static JedisConnection jedisConnection;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Override
    public boolean checkExistServer() {
        String pv=null;
        try{
            pv=this.getJedis().ping();
        }catch (Exception e){
            logger.warn("-----------未检查到可用redis服务!------------");
        }
        return StringUtils.isNotBlank(pv);
    }

    /**
     * 通过key删除（字节）
     *
     * @param key
     */
    public void del(byte[] key) {
        jedis=this.getJedis();
        try {
            jedis.del(key);
        }catch (Exception e){
            logger.error("",e);
        }finally {
            release(jedis);
        }
    }

    /**
     * 通过key删除
     *
     * @param key
     */
    public void del(String key) {
        jedis=this.getJedis();
        try {
            jedis.del(key);
        }catch (Exception e){
            logger.error("del key"+key,e);
        }finally {
           release(jedis);
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
        jedis=this.getJedis();
        this.set(key, value);
        try {
            jedis.expire(key, liveTime);
        }catch (Exception e){
            logger.error("",e);
        }finally {
            release(jedis);
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
        jedis=this.getJedis();
        this.set(key, value);
        try {
            jedis.expire(key, liveTime);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
    }

    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        jedis=this.getJedis();
        try {
            jedis.set(key, value);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
    }

    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        jedis=this.getJedis();
        try {
            jedis.set(key, value);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
    }

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    public String get(String key) {
        jedis=this.getJedis();
        String a = "";
        try {
            a = jedis.get(key);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
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
        jedis=this.getJedis();
        byte[]  a = null;
        try {
            a = jedis.get(key);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
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
        jedis=this.getJedis();
        Set<String> a = null;
        try {
            a = jedis.keys(pattern);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
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
        jedis=this.getJedis();
        boolean a = false;
        try {
            a = jedis.exists(key);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
        return a;
    }

    /**
     * 清空redis 所有数据
     *
     * @return
     */
    public String flushDB() {
        jedis=this.getJedis();
        String a = "";
        try {
            a = jedis.flushDB();
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
        return a;
    }

    /**
     * 查看redis里有多少数据
     */
    public long dbSize() {
        jedis=this.getJedis();
        long a = 0;
        try {
            a = jedis.dbSize();
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
        return a;
    }


    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
    public void setList(String key, String... value){
        jedis=this.getJedis();
        try {
            jedis.rpush(key, value);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
    }

    @Override
    public void zadd(String key, double score, String member) {
        jedis=this.getJedis();
        try {
            jedis.zadd(key, score, member);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
    }

    @Override
    public void zrem(String key, String member) {
        jedis=this.getJedis();
        try {
            jedis.zrem(key,member);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
    }

    @Override
    public Set zrange(String key, long start, long end) {
        jedis=this.getJedis();

        try {
            return jedis.zrange(key,start,end);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 通过key删除
     *
     * @param key
     */
    public String delList(String key) {
        jedis=this.getJedis();

        try {
            return jedis.lpop(key);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 通过key 修改 第几个要素
     *
     * @param key
     */
    public String setListSub(String key, String value) {
        jedis=this.getJedis();

        try {
            return jedis.lset(key, Long.valueOf(value), "0.00000");
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 通过key获取
     *
     * @param key
     */
    public List<String> getList(String key){
        jedis=this.getJedis();

        List<String> lists = null;
        try {
            lists =  jedis.lrange(key, 0, -1);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
        return lists;
    }

    /**
     * 通过key获取
     *
     * @param key
     */
    public List<String> getListByPage(String key, int start, int end){
        jedis=this.getJedis();
        List<String> lists = null;
        try {
            lists =  jedis.lrange(key,start,end);
        }catch (Exception e){
            logger.error("", e);
        }finally {
            release(jedis);
        }
        return lists;
    }
    /**
     * 通过key获取
     *
     * @param key
     */
    public List<String> getListSize(String key){
        jedis=this.getJedis();

        List<String> listString =  null;
        long listSize = 0;
        try {
            listString = new ArrayList<String>();
            for (int i = 1; i < 15; i++) {
                listSize = jedis.llen(String.valueOf(i));
                listString.add(String.valueOf(listSize));
            }
        }catch (Exception e){
            logger.error("",e);
        }finally {
            release(jedis);
        }
        return listString;
    }


    /**
     * 获取一个jedis 客户端
     *
     * @return
     */
    private Jedis getJedis() {
        if (jedis!=null&&jedis.isConnected())
            return jedis;

        if (jedisConnection == null) {
            jedisConnection = jedisConnectionFactory.getConnection();
        }
        return jedisConnection.getNativeConnection();
    }


    /**
     * 释放redis连接资源
     * @param jedis
     */
    public void release(Jedis jedis) {
        if (jedis!=null&&jedis.isConnected())
            jedis.close();
    }
}
