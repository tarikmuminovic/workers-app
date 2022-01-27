package com.teamway.test.workersapp.rest.controller;

import com.teamway.test.workersapp.application.WorkerShiftService;
import com.teamway.test.workersapp.application.command.AssignShift;
import com.teamway.test.workersapp.application.command.RemoveShift;
import com.teamway.test.workersapp.domain.model.Shift;
import com.teamway.test.workersapp.domain.view.ShiftView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/workers/{workerId}/shifts")
public class ShiftController {

	private final WorkerShiftService workerShiftService;

	@Autowired
	public ShiftController(WorkerShiftService workerShiftService) {
		this.workerShiftService = workerShiftService;
	}

	@GetMapping("")
	public ResponseEntity<Map<LocalDate, ShiftView>> findAllShifts(
			@PathVariable("workerId") Integer workerId) {
		Map<LocalDate, Shift> shifts = workerShiftService.findWorkersShifts(workerId);

		return new ResponseEntity<>(ShiftView.fromShiftMapToMapOfViews(shifts), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<ShiftView> assignShift(
			@PathVariable("workerId") Integer workerId, @RequestBody AssignShift command) {
		Shift shift = workerShiftService.assignShift(workerId, command);

		return new ResponseEntity<>(ShiftView.fromShift(shift), HttpStatus.OK);
	}

	@GetMapping("/{date}")
	public ResponseEntity<ShiftView> findShift(
			@PathVariable("workerId") Integer workerId,
			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		Shift shift = workerShiftService.getShiftByDateForWorker(workerId, date);

		return new ResponseEntity<>(ShiftView.fromShift(shift), HttpStatus.OK);
	}

	@DeleteMapping("")
	public ResponseEntity<Void> removeShift(
			@PathVariable("workerId") Integer workerId, @RequestBody RemoveShift command) {
		workerShiftService.removeShift(workerId, command);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
