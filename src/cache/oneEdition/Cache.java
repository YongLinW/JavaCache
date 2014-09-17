package cache.oneEdition;

/**
 * 缓存接口
 * 
 * @author Wen
 *
 */
public interface Cache<K, V> {
	/**
	 * 返回当前缓存的大�?
	 * 
	 * @return
	 */
	int size();

	/**
	 * 返回默认存活时间
	 * 
	 * @return
	 */
	long getDefaultExpire();

	/**
	 * 向缓存添加value对象,其在缓存中生存时间为默认�?
	 * 
	 * @param key
	 * @param value
	 */
	void put(K key, V value);

	/**
	 * 向缓存添加value对象,并指定存活时�?
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 *            过期时间
	 */
	void put(K key, V value, long expire);

	/**
	 * 查找缓存对象
	 * 
	 * @param key
	 * @return
	 */
	V get(K key);

	/**
	 * 淘汰对象
	 * 
	 * @return 被删除对象大�?
	 */
	int eliminate();

	/**
	 * 缓存是否已经�?
	 * 
	 * @return
	 */
	boolean isFull();

	/**
	 * 删除缓存对象
	 * 
	 * @param key
	 */
	void remove(K key);

	/**
	 * 清除�?��缓存对象
	 */
	void clear();

	/**
	 * 返回缓存大小
	 * 
	 * @return
	 */
	int getCacheSize();

	/**
	 * 缓存中是否为�?
	 */
	boolean isEmpty();

}
