package com.teamway.test.workersapp.domain.view;

import com.teamway.test.workersapp.domain.model.Shift;
import com.teamway.test.workersapp.domain.model.ShiftType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

public record ShiftView(LocalTime start, LocalTime end, ShiftType type) {

	public static ShiftView fromShift(Shift shift) {
		return new ShiftView(shift.getStart(), shift.getEnd(), shift.getType());
	}

	public static Map<LocalDate, ShiftView> fromShiftMapToMapOfViews(Map<LocalDate, Shift> shifts) {
		Map<LocalDate, ShiftView> views = new LinkedHashMap<>();
		shifts.forEach((date, shift) -> views.put(date, ShiftView.fromShift(shift)));

		return views;
	}
}
