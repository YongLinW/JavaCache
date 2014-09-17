package cache.oneEdition;

import java.util.HashMap;
import java.util.Iterator;

//LFU实现
public class LFUCache<K, V> extends AbstractCacheMap<K, V> {

	public LFUCache(int cacheSize, long defaultExpire) {
		super(cacheSize, defaultExpire);
		cacheMap = new HashMap<K, CacheObject<K, V>>(cacheSize + 1);
	}

	/**
	 * 实现删除过期对象 �?删除访问次数�?��的对�?
	 * 
	 */
	@Override
	protected int eliminateCache() {
		Iterator<CacheObject<K, V>> iterator = cacheMap.values().iterator();
		int count = 0;
		long minAccessCount = Long.MAX_VALUE;
		while (iterator.hasNext()) {
			CacheObject<K, V> cacheObject = iterator.next();

			if (cacheObject.isExpired()) {
				iterator.remove();
				count++;
				continue;
			} else {
				minAccessCount = Math.min(cacheObject.accessCount,
						minAccessCount);
			}
		}

		if (count > 0)
			return count;

		if (minAccessCount != Long.MAX_VALUE) {

			iterator = cacheMap.values().iterator();

			while (iterator.hasNext()) {
				CacheObject<K, V> cacheObject = iterator.next();

				cacheObject.accessCount -= minAccessCount;

				if (cacheObject.accessCount <= 0) {
					iterator.remove();
					count++;
				}

			}

		}

		return count;
	}

}
