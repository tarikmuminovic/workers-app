package com.teamway.test.workersapp.domain.repository;

import com.teamway.test.workersapp.domain.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

}
