package com.teamway.test.workersapp.application.command;

import com.teamway.test.workersapp.domain.model.ShiftType;

import java.time.LocalDate;

public record AssignShift(LocalDate date, ShiftType type) {

}
