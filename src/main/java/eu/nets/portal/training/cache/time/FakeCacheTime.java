package eu.nets.portal.training.cache.time;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("fakeCacheTime")
public class FakeCacheTime extends RealCacheTime {
	
	
	private long timeOffset = 0;

	private long frozenTime = 0;

	private boolean isFrozenTime = false;

	@Value("${cache.timeout}")
	private Integer timeout;

	@Value("${cache.isenabled}")
	private Boolean isCacheEnabled;

	@Override
	public long getCurrentTimestamp() {
		if (isFrozenTime) {
			return frozenTime + timeOffset;
		}
		else {
			return super.getCurrentTimestamp() + timeOffset;
		}
	}

	public long getRandomValidTimestamp() {
		return getEarliestValidCacheTimestamp() + (long) (Math.random()* timeout);
	}

	public long getRandomInvalidTimestamp() {
		return getEarliestValidCacheTimestamp() - 1 - (long) (Math.random() * 1000L * 60L * 60L * 24L * 30L);
	}

	public void jumpTwoMinutes() {
		timeOffset += 120000;
	}

	public void freezeTime() {
		frozenTime = super.getCurrentTimestamp();
		isFrozenTime = true;
	}

	public void unfreezeTime() {
		isFrozenTime = false;
	}
}