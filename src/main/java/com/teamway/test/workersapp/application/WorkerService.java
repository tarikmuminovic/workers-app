package com.teamway.test.workersapp.application;

import com.teamway.test.workersapp.application.command.RegisterWorker;
import com.teamway.test.workersapp.domain.exception.WorkerNotFoundException;
import com.teamway.test.workersapp.domain.model.Worker;
import com.teamway.test.workersapp.domain.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

	private final WorkerRepository workerRepository;

	@Autowired
	public WorkerService(WorkerRepository workerRepository) {
		this.workerRepository = workerRepository;
	}

	public Worker register(RegisterWorker command) {
		Worker worker = new Worker(command.name());
		return workerRepository.save(worker);
	}

	public void remove(Integer workerId) {
		workerRepository.deleteById(workerId);
	}

	public Worker get(Integer workerId) {
		return workerRepository.findById(workerId)
							   .orElseThrow(
									   () -> new WorkerNotFoundException("Cannot find worker with ID of " + workerId));
	}
}
