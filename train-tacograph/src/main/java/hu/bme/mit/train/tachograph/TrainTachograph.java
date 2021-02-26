package hu.bme.mit.train.tachograph;

import java.time.LocalDateTime;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TrainTachograph {
	private Table<LocalDateTime, String, Object> table = HashBasedTable.create();

	public void addRecord(LocalDateTime date, Object position, Object referenceSpeed) {
		table.put(date, "position", position);
		table.put(date, "referenceSpeed", referenceSpeed);
	}

	public int recordCount() {
		return table.size();
	}
}
