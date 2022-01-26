package com.teamway.test.workersapp.domain.model;

import com.teamway.test.workersapp.domain.exception.ShiftAlreadyAssignedException;
import com.teamway.test.workersapp.domain.exception.ShiftNotFoundException;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "worker")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TypeDefs(value = {@TypeDef(name = "json", typeClass = JsonStringType.class)})
public class Worker {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name")
	private String name;

	@Type(type = "json")
	@Column(name = "shifts", columnDefinition = "json")
	private Map<LocalDate, Shift> shifts;

	public Worker(String name) {
		this.name = name;
		this.shifts = new HashMap<>();
	}

	public Worker assignShift(LocalDate date, Shift shift) {
		if (isShiftAssigned(date)) {
			throw new ShiftAlreadyAssignedException("Cannot assign multiple shifts to worker on same day");
		}
		shifts.put(date, shift);
		return this;
	}

	public Boolean isShiftAssigned(LocalDate date) {
		return shifts.containsKey(date);
	}

	public Shift getShift(LocalDate date) {
		Shift shift = shifts.get(date);

		if (shift == null) {
			throw new ShiftNotFoundException("Worker with ID " + id + " is not assigned to shift for day " + date);
		}
		return shift;
	}

	public Worker removeFromShift(LocalDate date) {
		if (!isShiftAssigned(date)) {
			throw new ShiftNotFoundException("Worker with ID " + id + " is not assigned to shift for day " + date);
		}

		shifts.remove(date);
		return this;
	}
}
