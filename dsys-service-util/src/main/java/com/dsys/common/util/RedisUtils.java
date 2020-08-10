package com.dsys.common.util;

import com.dsys.common.sdk.redis.redisson.connproperties.RedisProperties;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 
 * ClassName: RedisUtils
 * 
 * @Description: 缓存管理工具
 * @author shilp
 * @date 2019年11月5日
 */
@Component
public abstract class RedisUtils implements RedissonClient{

	@Autowired
	private RedissonClient redissonClient;
	
	@Autowired
	private RedisProperties redisProperties;
	
	/**
	 * 设置对象的值
	 *
	 * @param name  键
	 * @param value 值
	 * @return
	 */
	public <T> void setValue(String name, T value) {
		setValue(name,value,(long)redisProperties.getTimeout());
	}
	
	/**
	 * 设置对象的值
	 *
	 * @param name  键
	 * @param value 值
	 * @param time  缓存时间 单位毫秒 -1 永久缓存
	 * @return
	 */
	public <T> void setValue(String name, T value, Long time) {
		RBucket<Object> bucket = redissonClient.getBucket(name);
		if(time==-1){
			bucket.set(value);
		}else {
			bucket.set(value, time, TimeUnit.MILLISECONDS);
		}
	}
	
	/**
	 * 如果值已经存在则则不设置
	 *
	 * @param name  键
	 * @param value 值
	 * @param time  缓存时间 单位毫秒
	 * @return true 设置成功,false 值存在,不设置
	 */
	public <T> Boolean trySetValue(String name, T value, Long time) {
		RBucket<Object> bucket = redissonClient.getBucket(name);
		boolean b;
		if(time==-1){
			b = bucket.trySet(value);
		}else {
			b = bucket.trySet(value, time, TimeUnit.MILLISECONDS);
		}
		return b;
	}
	
	/**
	 * 如果值已经存在则则不设置
	 *
	 * @param name  键
	 * @param value 值
	 * @return true 设置成功,false 值存在,不设置
	 */
	public <T> Boolean trySetValue(String name, T value) {
		return trySetValue(name,value,(long)redisProperties.getTimeout());
	}

	/**
	 * ` 获取字符串对象
	 *
	 * @param objectName
	 * @return
	 */
	public  <T> RBucket<T> getRBucket(String objectName) {
		RBucket<T> bucket = redissonClient.getBucket(objectName);
		return bucket;
	}

	/**
	 * 获取Map对象
	 *
	 * @param objectName
	 * @return
	 */
	public <K, V> RMap<K, V> getRMap(String objectName) {
		RMap<K, V> map = redissonClient.getMap(objectName);
		return map;
	}
	
	/**
	 * 设置map集合
	 * @param name
	 * @param data
	 * @param time 缓存时间,单位毫秒 -1永久缓存
	 * @return
	 */
	public void setMapValues(String name,Map data,Long time){
		RMap map = redissonClient.getMap(name);
		Long dataValidTime = (long)redisProperties.getTimeout();
		if(time!=-1){
			map.expire(dataValidTime, TimeUnit.MILLISECONDS);
		}
		map.putAll(data);
	}

	/**
	 * 获取有序集合
	 *
	 * @param objectName
	 * @return
	 */
	public <V> RSortedSet<V> getRSortedSet(String objectName) {
		RSortedSet<V> sortedSet = redissonClient.getSortedSet(objectName);
		return sortedSet;
	}
	
	/**
	 * 设置List集合
	 * @param name
	 * @param data
	 * @param time 缓存时间,单位毫秒 -1永久缓存
	 * @return
	 */
	public void setListValues(String name, List data, Long time){
		RList list = redissonClient.getList(name);
		Long dataValidTime = (long)redisProperties.getTimeout();
		if(time!=-1){
			list.expire(dataValidTime, TimeUnit.MILLISECONDS);
		}
		list.addAll(data);
	}
	
	/**
	 * 设置List集合
	 * @param name
	 * @param data
	 * @return
	 */
	public void setListValues(String name, List data){
		setListValues(name,data,(long)redisProperties.getTimeout());
	}
	
	/**
	 * 设置set集合
	 * @param name
	 * @param data
	 * @param time 缓存时间,单位毫秒 -1永久缓存
	 * @return
	 */
	public void setSetValues(String name,Set data,Long time){
		RSet set = redissonClient.getSet(name);
		Long dataValidTime = (long)redisProperties.getTimeout();
		if(time!=-1){
			set.expire(dataValidTime, TimeUnit.MILLISECONDS);
		}
		set.addAll(data);
	}
	/**
	 * 设置set集合
	 * @param name
	 * @param data
	 * @return
	 */
	public void setSetValues(String name, Set data){
		setSetValues(name,data,(long)redisProperties.getTimeout());
	}

	/**
	 * 获取集合
	 *
	 * @param objectName
	 * @return
	 */
	public <V> RSet<V> getRSet(String objectName) {
		RSet<V> rSet = redissonClient.getSet(objectName);
		return rSet;
	}

	/**
	 * 获取列表
	 *
	 * @param objectName
	 * @return
	 */
	public <V> RList<V> getRList(String objectName) {
		RList<V> rList = redissonClient.getList(objectName);
		return rList;
	}
	
	

	/**
	 * 获取队列
	 *
	 * @param objectName
	 * @return
	 */
	public <V> RQueue<V> getRQueue(String objectName) {
		RQueue<V> rQueue = redissonClient.getQueue(objectName);
		return rQueue;
	}

	/**
	 * 获取双端队列
	 *
	 * @param objectName
	 * @return
	 */
	public <V> RDeque<V> getRDeque(String objectName) {
		RDeque<V> rDeque = redissonClient.getDeque(objectName);
		return rDeque;
	}

	/**
	 * 获取锁
	 *
	 * @param objectName
	 * @return
	 */
	public RLock getRLock(String objectName) {
		RLock rLock = redissonClient.getLock(objectName);
		return rLock;
	}

	/**
	 * 获取读取锁
	 *
	 * @param objectName
	 * @return
	 */
	public RReadWriteLock getRWLock(String objectName) {
		RReadWriteLock rwlock = redissonClient.getReadWriteLock(objectName);
		return rwlock;
	}

	/**
	 * 获取原子数
	 *
	 * @param objectName
	 * @return
	 */
	public RAtomicLong getRAtomicLong(String objectName) {
		RAtomicLong rAtomicLong = redissonClient.getAtomicLong(objectName);
		return rAtomicLong;
	}

	/**
	 * 获取记数锁
	 *
	 * @param objectName
	 * @return
	 */
	public RCountDownLatch getRCountDownLatch(String objectName) {
		RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch(objectName);
		return rCountDownLatch;
	}

}
