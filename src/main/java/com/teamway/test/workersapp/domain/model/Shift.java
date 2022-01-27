package com.teamway.test.workersapp.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalTime;

public class Shift {

	private LocalTime start;

	private LocalTime end;

	private ShiftType type;

	public LocalTime getStart() {
		return start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public ShiftType getType() {
		return type;
	}

	@JsonCreator
	public Shift(ShiftType type) {
		this.type = type;
		switch (type) {
			case ZERO_TO_EIGHT -> {
				this.start = LocalTime.of(0, 0, 0);
				this.end = LocalTime.of(8, 0, 0);
			}
			case EIGHT_TO_SIXTEEN -> {
				this.start = LocalTime.of(8, 0, 0);
				this.end = LocalTime.of(16, 0, 0);
			}
			case SIXTEEN_TO_TWENTY_FOUR -> {
				this.start = LocalTime.of(16, 0, 0);
				this.end = LocalTime.of(24, 0, 0);
			}
		}
	}
}
