package com.qufenqi.common.service;/**
 * Created by zhangyang on 20/8/2016.
 */

import java.util.List;
import java.util.Set;

/**
 * 缓存管理
 *
 * @author zhangyang
 * @create 2016-08-20
 */
public interface CacheManagerService {
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
     * @param liveTime
     */
    void set(byte[] key, byte[] value, int liveTime);
    /**
     * 添加key value 并且设置存活时间
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
    String clear();
    /**
     * 查看redis里有多少数据
     */
    long size();
    /**
     *
     * @param key
     * @param value
     */
    void setObject(String key, Object value);
    /**
     *
     * @param key
     * @param value
     * @param liveTime
     */
    void setObject(String key, Object value, int liveTime);
    /**
     *
     * @param key
     * @return
     */
    Object getObject(String key);
    /**
     *
     * @param key
     * @return
     */
    String delList(String key);

    /**
     *
     * @param key
     * @return
     */
    List<String> getList(String key);

    /**
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> getListByPage(String key, int start, int end);
}
