package com.csdlpt.backend.repository;

import com.csdlpt.backend.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Integer> {
}
