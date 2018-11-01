package eu.nets.portal.training.cache;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 * 
 * A singleton class to cache API result for a specific time
 * 
 * @author vipin
 *
 */
@Component
public class CacheProvider {
	
	private Date lastAccessed;
	
	private Map dataMap;

	public Date getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(Date lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public Map getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map dataMap) {
		this.dataMap = dataMap;
	}

}
