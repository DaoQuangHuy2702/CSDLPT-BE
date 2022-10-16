package com.csdlpt.backend.service;

import com.csdlpt.backend.entity.BranchEntity;
import com.csdlpt.backend.entity.EmployeeEntity;
import com.csdlpt.backend.mapper.BranchMapper;
import com.csdlpt.backend.model.branch.BranchList;
import com.csdlpt.backend.model.branch.BranchReq;
import com.csdlpt.backend.model.branch.BranchRes;
import com.csdlpt.backend.repository.BranchRepository;
import com.csdlpt.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService {
    private final BranchMapper mapper;
    private final BranchRepository repo;
    private final EmployeeRepository empRepo;

    @Autowired
    public BranchService(BranchMapper mapper, BranchRepository repo, EmployeeRepository empRepo) {
        this.mapper = mapper;
        this.repo = repo;
        this.empRepo = empRepo;
    }

    public BranchList getAllBranch() {
        List<BranchEntity> entityList = repo.findAll().stream().filter(
                branchEntity -> branchEntity.getDeletedAt() == null
        ).collect(Collectors.toList());

        BranchList list = mapper.mapBranchListFromBranchEntities(entityList);

        return list;
    }

    public BranchRes addBranch(BranchReq branchReq) {
        BranchEntity branchEntity = mapper.mapBranchEntityFromBranchReq(branchReq);
        BranchEntity saved = repo.save(branchEntity);

        return mapper.mapBranchResFromBranchEntity(saved);
    }

    public BranchRes getBranch(int id) {
        BranchEntity branchEntity = repo.getById(id);

        if(branchEntity.getDeletedAt() != null) {
            return new BranchRes();
        }

        return mapper.mapBranchResFromBranchEntity(branchEntity);
    }

    public BranchRes updateBranch(int id, BranchReq branchReq) {
        BranchEntity branchEntity = mapper.mapBranchEntityFromBranchReq(id, branchReq);
        BranchEntity saved = repo.save(branchEntity);

        return mapper.mapBranchResFromBranchEntity(saved);
    }

    public void deleteBranch(int id) {
        BranchEntity deptEntity = repo.getById(id);
        Date current = new Date();
        List<EmployeeEntity> entityList = empRepo.findAllByBranchId(id);

        entityList.stream().forEach(employeeEntity -> {
            employeeEntity.setDeletedAt(current);
            empRepo.save(employeeEntity);
        });

        deptEntity.setDeletedAt(current);

        repo.save(deptEntity);
    }
}
