package hu.bme.mit.train.tachograph;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class TrainTachographTest {

	@Test
	public void TestAddRecord() {
		TrainTachograph t = new TrainTachograph();
		t.addRecord(LocalDateTime.now(), 1, 2);
		Assert.assertTrue(t.recordCount() > 0);
	}
}
