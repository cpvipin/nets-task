package eu.nets.portal.training.cache.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import eu.nets.portal.training.cache.time.FakeCacheTime;
import eu.nets.portal.training.cache.time.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
/**
 * For unit testing cache time.
 * 
 * @author vipin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FakeCacheTimeTest {

	@Autowired
    private MockMvc mvc;

	@Autowired
	@Qualifier("fakeCacheTime")
	Time time;

	@Autowired
	FakeCacheTime fakeCacheTime;

	@Test
	public void testTimeIsFakeTime() {
		assertTrue(time instanceof FakeCacheTime);
	}

	@Test
	public void testTimeIsSingleton() {
		assertTrue(time == fakeCacheTime);
		assertEquals(time.getCurrentTimestamp(), fakeCacheTime.getCurrentTimestamp());
		fakeCacheTime.jumpTwoMinutes();
		assertEquals(time.getCurrentTimestamp(), fakeCacheTime.getCurrentTimestamp());
	}

	@Test
	public void testFakeTCacheTimeJumpByTwoMinutes() {
		fakeCacheTime.freezeTime();
		final long timestampBeforeJump = fakeCacheTime.getCurrentTimestamp();
		fakeCacheTime.jumpTwoMinutes();
		final long timestampAfterJump = fakeCacheTime.getCurrentTimestamp();
		assertEquals(timestampAfterJump, timestampBeforeJump + 120000);
		fakeCacheTime.unfreezeTime();
	}

	@Test
	public void testFakeCacheTimeFreeze() throws InterruptedException {
		fakeCacheTime.freezeTime();
		final long timestampDuringFreezeAndBeforeFirstSleep = fakeCacheTime.getCurrentTimestamp();
		Thread.sleep(10);
		final long timestampDuringFreezeAndAfterFirstSleep = fakeCacheTime.getCurrentTimestamp();
		fakeCacheTime.unfreezeTime();
		Thread.sleep(10);
		final long timestampAfterFreezeAndAfterSecondSleep = fakeCacheTime.getCurrentTimestamp();
		assertEquals(timestampDuringFreezeAndBeforeFirstSleep, timestampDuringFreezeAndAfterFirstSleep);
		assertNotEquals(timestampDuringFreezeAndBeforeFirstSleep, timestampAfterFreezeAndAfterSecondSleep);
	}

}