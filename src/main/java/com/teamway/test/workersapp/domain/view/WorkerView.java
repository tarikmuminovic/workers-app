package com.teamway.test.workersapp.domain.view;

import com.teamway.test.workersapp.domain.model.Worker;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public record WorkerView(Integer id, String name, Map<LocalDate, ShiftView> shifts) {

	public static WorkerView fromWorker(Worker worker) {
		Map<LocalDate, ShiftView> shiftViews = new LinkedHashMap<>();
		worker.getShifts().forEach((localDate, shift) -> shiftViews.put(localDate, ShiftView.fromShift(shift)));

		return new WorkerView(worker.getId(), worker.getName(), shiftViews);
	}
}
