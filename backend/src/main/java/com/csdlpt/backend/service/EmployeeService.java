package com.csdlpt.backend.service;

import com.csdlpt.backend.entity.EmployeeEntity;
import com.csdlpt.backend.mapper.EmployeeMapper;
import com.csdlpt.backend.model.employee.EmployeeList;
import com.csdlpt.backend.model.employee.EmployeeReq;
import com.csdlpt.backend.model.employee.EmployeeRes;
import com.csdlpt.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeMapper mapper;
    private final EmployeeRepository repo;

    @Autowired
    public EmployeeService(EmployeeMapper mapper, EmployeeRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public EmployeeList getAllEmployee() {
        List<EmployeeEntity> entityList = repo.findAll().stream().filter(
                employeeEntity -> employeeEntity.getDeletedAt() == null
        ).collect(Collectors.toList());

        EmployeeList list = mapper.mapEmployeeListFromEmployeeEntities(entityList);

        return list;
    }

    public EmployeeList getAllEmployeeByBranchId(int branchId){
        List<EmployeeEntity> entityList = repo.findAllByBranchId(branchId).stream().filter(
                employeeEntity -> employeeEntity.getDeletedAt() == null
        ).collect(Collectors.toList());

        EmployeeList list = mapper.mapEmployeeListFromEmployeeEntities(entityList);

        return list;
    }

    public EmployeeRes addEmployee(EmployeeReq empReq) {
        EmployeeEntity empEntity = mapper.mapEmployeeEntityFromEmployeeReq(empReq);
        EmployeeEntity saved = repo.save(empEntity);

        return mapper.mapEmployeeResFromEmployeeEntity(saved);
    }

    public EmployeeRes getEmployee(int id) {
        EmployeeEntity empEntity = repo.getById(id);

        if(empEntity.getDeletedAt() != null) {
            return new EmployeeRes();
        }

        return mapper.mapEmployeeResFromEmployeeEntity(empEntity);
    }

    public EmployeeRes updateEmployee(int id, EmployeeReq empReq) {
        EmployeeEntity empEntity = mapper.mapEmployeeEntityFromEmployeeReq(id, empReq);
        EmployeeEntity saved = repo.save(empEntity);

        return mapper.mapEmployeeResFromEmployeeEntity(saved);
    }

    public EmployeeRes login(String email, String password) throws Exception {
        EmployeeEntity empEntity = repo.getEmployeeEntityByEmailAndPassword(email, password);

        if(empEntity.getDeletedAt() != null) {
            throw new Exception();
        }

        return mapper.mapEmployeeResFromEmployeeEntity(empEntity);
    }

    public void deleteEmployee(int id) {
        EmployeeEntity empEntity = repo.getById(id);
        Date current = new Date();

        empEntity.setDeletedAt(current);

        repo.save(empEntity);
    }
}
