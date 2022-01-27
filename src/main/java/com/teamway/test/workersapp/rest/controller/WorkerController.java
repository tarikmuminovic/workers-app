package com.teamway.test.workersapp.rest.controller;

import com.teamway.test.workersapp.application.WorkerService;
import com.teamway.test.workersapp.application.command.RegisterWorker;
import com.teamway.test.workersapp.domain.model.Worker;
import com.teamway.test.workersapp.domain.view.WorkerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/workers")
public class WorkerController {

	private final WorkerService workerService;

	@Autowired
	public WorkerController(
			WorkerService workerService) {
		this.workerService = workerService;
	}

	@PostMapping("")
	public ResponseEntity<WorkerView> registerWorker(@RequestBody RegisterWorker command) {
		Worker worker = workerService.register(command);

		return new ResponseEntity<>(WorkerView.fromWorker(worker), HttpStatus.CREATED);
	}

	@GetMapping("/{workerId}")
	public ResponseEntity<WorkerView> findWorker(@PathVariable Integer workerId) {
		Worker worker = workerService.get(workerId);

		return new ResponseEntity<>(WorkerView.fromWorker(worker), HttpStatus.OK);
	}

	@DeleteMapping("/{workerId}")
	public ResponseEntity<Void> removeWorker(
			@PathVariable("workerId") Integer workerId) {
		workerService.remove(workerId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
