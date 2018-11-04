package eu.nets.portal.training.cache;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.nets.portal.training.cache.time.Time;

/**
 * 
 * A singleton class to cache API result for a specific time
 * 
 * @author vipin
 *
 */
@Component
public class CacheProvider {

	@Autowired
	private Time time;

	private long lastAccessed;

	private Map dataMap;

	public long getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public Map getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map dataMap) {
		this.dataMap = dataMap;
	}

	public boolean isCacheValid() {
		return this.getDataMap() != null && time.isCacheValid(getLastAccessed());
	}

}
