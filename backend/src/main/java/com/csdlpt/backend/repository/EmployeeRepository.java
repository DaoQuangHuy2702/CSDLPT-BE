package com.csdlpt.backend.repository;

import com.csdlpt.backend.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    List<EmployeeEntity> findAllByBranchId(int branchId);
    EmployeeEntity getEmployeeEntityByEmailAndPassword(String email, String password);
}
