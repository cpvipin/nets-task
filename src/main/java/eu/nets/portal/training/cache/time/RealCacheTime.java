package eu.nets.portal.training.cache.time;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Implementation of <see>eu.nets.portal.training.cache.Time.class</see>
 * Manages time for cache holding , and check if cache is invalid as per the 
 * configuration provided in <code>application.properties</code>.
 * Primary bean for Time interface. 
 * See another qualifier bean <see>eu.nets.portal.training.cache.FakeCacheTime.class</see> for testing cache Time. 
 * </p>
 * 
 * @author vipin
 *
 */
@Component
@Primary
public class RealCacheTime implements Time
{
	

    @Value("${cache.timeout}")
    private Integer timeout;

    @Value("${cache.isenabled}")
    private Boolean isCacheEnabled;
    
    
    
	@Override
	public long getCurrentTimestamp()
	{
		return System.currentTimeMillis();
	}

	@Override
	public long getEarliestValidCacheTimestamp() {
		return getCurrentTimestamp() - timeout;
	}

	@Override
	public boolean isCacheValid(final long timestamp)
	{
		return isCacheEnabled && this.isValidCacheTimestamp(timestamp);
	}
	
	public boolean isValidCacheTimestamp(final long timestamp)
	{
		return timestamp >= getEarliestValidCacheTimestamp();
	}
	
	
}