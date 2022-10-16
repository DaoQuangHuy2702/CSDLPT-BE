package com.csdlpt.backend.mapper;

import com.csdlpt.backend.entity.EmployeeEntity;
import com.csdlpt.backend.model.employee.EmployeeList;
import com.csdlpt.backend.model.employee.EmployeeReq;
import com.csdlpt.backend.model.employee.EmployeeRes;
import com.csdlpt.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeMapper {
    private final EmployeeRepository repo;

    @Autowired
    public EmployeeMapper(EmployeeRepository repo) {
        this.repo = repo;
    }

    public EmployeeRes mapEmployeeResFromEmployeeEntity(EmployeeEntity from) {
        EmployeeRes to = new EmployeeRes();

        to.setId(from.getId());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setPassword(from.getPassword());
        to.setPhone(from.getPhone());
        to.setRole(from.getRole());
        to.setBranchId(from.getBranchId());
        to.setCreatedAt(from.getCreatedAt());
        to.setUpdatedAt(from.getUpdatedAt());

        return to;
    }

    public EmployeeList mapEmployeeListFromEmployeeEntities(List<EmployeeEntity> from) {
        EmployeeList to = new EmployeeList();

        from.stream().forEach(employee -> {
            to.add(mapEmployeeResFromEmployeeEntity(employee));
        });

        return to;
    }

    public EmployeeEntity mapEmployeeEntityFromEmployeeReq(EmployeeReq from) {
        EmployeeEntity to = new EmployeeEntity();

        Date current = new Date();

        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setPassword(from.getPassword());
        to.setPhone(from.getPhone());
        to.setRole(from.getRole());
        to.setBranchId(from.getBranchId());
        to.setCreatedAt(current);
        to.setUpdatedAt(current);

        return to;
    }

    public EmployeeEntity mapEmployeeEntityFromEmployeeReq(int id, EmployeeReq from) {
        EmployeeEntity to = repo.getById(id);
        Date current = new Date();

        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setPassword(from.getPassword());
        to.setPhone(from.getPhone());
        to.setRole(from.getRole());
        to.setBranchId(from.getBranchId());
        to.setUpdatedAt(current);

        return to;
    }
}

