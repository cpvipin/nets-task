package eu.nets.portal.training.cache.time;

/**
 * <p>
 * Interface for managing API cache time.
 * </p> 
 * @author vipin
 *
 */
public interface Time
{
   public long getCurrentTimestamp();
   public long getEarliestValidCacheTimestamp();
   public boolean isCacheValid(final long timestamp);
   public boolean isValidCacheTimestamp(final long timestamp);
   
}