package com.qufenqi.common.service;

import java.util.List;
import java.util.Set;


/**
 * Created by zhangyang on 2016/8/19.
 */
public interface RedisService {
    /**
     * 检查是否存在Redis服务,有则true
     * @return
     *
     */
    boolean checkExistServer();

    /**
     * 通过key删除（字节）
     *
     * @param key
     */
     void del(byte[] key);


    /**
     * 通过key删除
     *
     * @param key
     */
     void del(String key);


    /**
     * 添加key value 并且设置存活时间(秒)
     *
     * @param key
     * @param value
     * @param liveTime 秒
     */
     void set(byte[] key, byte[] value, int liveTime);


    /**
     * 添加key value 并且设置存活时间(秒)
     *
     * @param key
     * @param value
     * @param liveTime
     */
     void set(String key, String value, int liveTime);


    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
     void set(String key, String value);


    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
     void set(byte[] key, byte[] value);


    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
     String get(String key);


    /**
     * 获取redis value (byte [] )(反序列化)
     *
     * @param key
     * @return
     */
     byte[] get(byte[] key);


    /**
     * 通过正则匹配keys
     *
     * @param pattern
     * @return
     */
     Set<String> keys(String pattern);


    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
     boolean exists(String key);


    /**
     * 清空redis 所有数据
     *
     * @return
     */
     String flushDB();/**

     * 查看redis里有多少数据
     *
     * @return
     */
     long dbSize();

    /**
     * 添加list
     *
     * @param key
     * @param value
     */
     void setList(String key, String... value);
    /**
     * 删除list中一个key
     *
     * @param key
     */
     String delList(String key);
    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
     List<String> getList(String key);

    /**
     * 获取redis value (String)
     *
     * @param key start end
     * @return
     */
     List<String> getListByPage(String key, int start, int end);
    /**
     * 通过key 修改 第几个要素
     *
     * @param key
     */
     String setListSub(String key, String value);
    /**
     * 获取redis listsize
     *
     * @param key
     * @return
     */
     List<String> getListSize(String key);
    /**
     * 向名称为key的zset中添加元素member，score用于排序。如果该元素已经存在，则根据score更新该元素的顺序
     * */
     void zadd(String key, double score, String member);

    /**
     * 删除名称为key的zset中的元素member
     * */
     void zrem(String key, String member);

    /**
     * 返回名称为key的zset（元素已按score从小到大排序）中的index从start到end的所有元素
     * */
     Set zrange(String key, long start, long end);
}