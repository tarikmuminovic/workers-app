package com.teamway.test.workersapp.application;

import com.teamway.test.workersapp.application.command.AssignShift;
import com.teamway.test.workersapp.application.command.RemoveShift;
import com.teamway.test.workersapp.domain.exception.WorkerNotFoundException;
import com.teamway.test.workersapp.domain.model.Shift;
import com.teamway.test.workersapp.domain.model.Worker;
import com.teamway.test.workersapp.domain.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class WorkerShiftService {

	private final WorkerRepository workerRepository;

	@Autowired
	public WorkerShiftService(WorkerRepository workerRepository) {
		this.workerRepository = workerRepository;
	}

	public Shift assignShift(Integer workerId, AssignShift command) {
		Worker worker = this.workerRepository.findById(workerId)
											 .orElseThrow(() -> new WorkerNotFoundException(
													 "Cannot find worker with ID of " + workerId));

		Shift shift = new Shift(command.type());
		worker.assignShift(command.date(), shift);

		workerRepository.save(worker);

		return shift;
	}

	public void removeShift(Integer workerId, RemoveShift command) {
		Worker worker = this.workerRepository.findById(workerId)
											 .orElseThrow(() -> new WorkerNotFoundException(
													 "Cannot find worker with ID of " + workerId));

		worker.removeFromShift(command.date());
		workerRepository.save(worker);
	}

	public Shift getShiftByDateForWorker(Integer workerId, LocalDate date) {
		Worker worker = this.workerRepository.findById(workerId)
											 .orElseThrow(() -> new WorkerNotFoundException(
													 "Cannot find worker with ID of " + workerId));

		return worker.getShift(date);
	}

	public Map<LocalDate, Shift> findWorkersShifts(Integer workerId) {
		Worker worker = this.workerRepository.findById(workerId)
											 .orElseThrow(() -> new WorkerNotFoundException(
													 "Cannot find worker with ID of " + workerId));

		return worker.getShifts();
	}
}
