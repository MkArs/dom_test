package com.mkhitaryan.test.dom.service;

import com.mkhitaryan.test.dom.entity.Operation;

import java.sql.Date;
import java.util.List;

public interface OperationService {
    void saveOperation(Operation operation);

    List<Operation> showOperations(Date date);
}
