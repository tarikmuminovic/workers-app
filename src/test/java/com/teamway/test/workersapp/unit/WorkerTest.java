package com.teamway.test.workersapp.unit;

import com.teamway.test.workersapp.domain.exception.ShiftAlreadyAssignedException;
import com.teamway.test.workersapp.domain.exception.ShiftNotFoundException;
import com.teamway.test.workersapp.domain.model.Shift;
import com.teamway.test.workersapp.domain.model.ShiftType;
import com.teamway.test.workersapp.domain.model.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class WorkerTest {

	@Test
	public void tryToAssignShiftForEmptyDay_retrieveAssignedShiftSuccessfully() {
		Worker worker = new Worker("John");
		LocalDate today = LocalDate.now();

		ShiftType shiftType = ShiftType.EIGHT_TO_SIXTEEN;
		Shift expectedShift = new Shift(shiftType);

		worker.assignShift(today, expectedShift);

		Assertions.assertEquals(expectedShift, worker.getShift(today));
	}

	@Test
	public void tryToAssignDifferentShiftForAlreadyUsedDay_throwsShiftAlreadyAssignedException() {
		Worker worker = new Worker("John");
		LocalDate today = LocalDate.now();

		ShiftType assignedShiftType = ShiftType.EIGHT_TO_SIXTEEN;
		Shift assignedShift = new Shift(assignedShiftType);

		worker.assignShift(today, assignedShift);

		ShiftType shiftType = ShiftType.ZERO_TO_EIGHT;
		Shift shift = new Shift(shiftType);

		Assertions.assertThrows(ShiftAlreadyAssignedException.class, () -> worker.assignShift(today, shift));
	}

	@Test
	public void tryToGetNotAssignedShiftForDate_throwsShiftNotFoundException() {
		Worker worker = new Worker("John");
		LocalDate today = LocalDate.now();

		Assertions.assertThrows(ShiftNotFoundException.class, () -> worker.getShift(today));
	}

	@Test
	public void tryToGetAssignedShiftForDate_retrievesAssignedShift() {
		Worker worker = new Worker("John");
		LocalDate today = LocalDate.now();

		ShiftType assignedShiftType = ShiftType.EIGHT_TO_SIXTEEN;
		Shift expectedShift = new Shift(assignedShiftType);

		worker.assignShift(today, expectedShift);

		Assertions.assertEquals(expectedShift, worker.getShift(today));
	}

	@Test
	public void tryToRemoveFromNotAssignedShiftForDate_throwsShiftNotFoundException() {
		Worker worker = new Worker("John");
		LocalDate today = LocalDate.now();

		Assertions.assertThrows(ShiftNotFoundException.class, () -> worker.removeFromShift(today));
	}

	@Test
	public void tryToRemoveFromAssignedShiftForDate_throwsShiftNotFoundExceptionWhenTryToRetrieveIt() {
		Worker worker = new Worker("John");
		LocalDate today = LocalDate.now();

		ShiftType shiftType = ShiftType.EIGHT_TO_SIXTEEN;
		Shift shift = new Shift(shiftType);

		worker.assignShift(today, shift);
		worker.removeFromShift(today);

		Assertions.assertThrows(ShiftNotFoundException.class, () -> worker.getShift(today));
	}
}
