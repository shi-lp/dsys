package com.dsys.common.sdk.redisson;

import java.io.IOException;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RDeque;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RQueue;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSet;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * ClassName: RedisUtils
 * 
 * @Description: 缓存管理工具
 * @author shilp
 * @date 2019年11月5日
 */
public class RedisUtils {

	@Autowired
	private RedissonClient redissonClient;

	public void getRedissonClient() throws IOException {
		Config config = redissonClient.getConfig();
		System.out.println(config.toJSON().toString());
	}

	/**
	 * ` 获取字符串对象
	 *
	 * @param objectName
	 * @return
	 */
	public <T> RBucket<T> getRBucket(String objectName) {
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
