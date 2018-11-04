package eu.nets.portal.training.cache.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import eu.nets.portal.training.cache.time.RealCacheTime;
import eu.nets.portal.training.cache.time.Time;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RealCacheTimeTest {
	
	@Autowired
    private MockMvc mvc;

	@Autowired
	Time time;

	@Autowired
	RealCacheTime realCacheTime;

	@Test
	public void testTimeIsRealTime() {
		assertTrue(time instanceof RealCacheTime);
	}

	@Test
	public void testTimeIsSingleton() {
		assertTrue(time == realCacheTime);
		assertEquals(time.getCurrentTimestamp(), realCacheTime.getCurrentTimestamp());
	}

	@Test
	public void testTimeValidatesCorrectTimestamp() {
		assertTrue(time.isCacheValid(time.getCurrentTimestamp()));
		assertTrue(time.isCacheValid(time.getEarliestValidCacheTimestamp()));
	}

	@Test
	public void testTimeRejectsIncorrectTimestamp() {
		assertFalse(time.isValidCacheTimestamp(time.getEarliestValidCacheTimestamp() - 1));
	}

}