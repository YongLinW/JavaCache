package cache.oneEdition;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * FIFO实现
 * 
 * @author Wen
 *
 * @param <K>
 * @param <V>
 */
public class FIFOCache<K, V> extends AbstractCacheMap<K, V> {

	public FIFOCache(int cacheSize, long defaultExpire) {
		super(cacheSize, defaultExpire);
		cacheMap = new LinkedHashMap<K, CacheObject<K, V>>(cacheSize + 1);
	}

	@Override
	protected int eliminateCache() {

		int count = 0;
		K firstKey = null;

		Iterator<CacheObject<K, V>> iterator = cacheMap.values().iterator();
		while (iterator.hasNext()) {
			CacheObject<K, V> cacheObject = iterator.next();

			if (cacheObject.isExpired()) {
				iterator.remove();
				count++;
			} else {
				if (firstKey == null)
					firstKey = cacheObject.key;
			}
		}

		if (firstKey != null && isFull()) {// 删除过期对象还是�?继续删除链表第一�?
			cacheMap.remove(firstKey);
		}

		return count;
	}

}
