package com.mkhitaryan.test.dom.dao;

import com.mkhitaryan.test.dom.entity.Operation;

import java.sql.Date;
import java.util.List;

public interface OperationDAO {
    public void saveOperation(Operation operation);

    public List<Operation> showOperations(Date date);
}
