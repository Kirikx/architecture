package ru.kirikomp.arch.service;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TimeManagementService {

    LocalDateTime getTimeProductionSpec(UUID specId);
    LocalDateTime getTimeProductionProd(UUID prodId);
}
