package com.csdlpt.backend.service;

import com.csdlpt.backend.entity.VendorEntity;
import com.csdlpt.backend.mapper.VendorMapper;
import com.csdlpt.backend.model.vendor.VendorList;
import com.csdlpt.backend.model.vendor.VendorReq;
import com.csdlpt.backend.model.vendor.VendorRes;
import com.csdlpt.backend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {
    private final VendorMapper mapper;
    private final VendorRepository repo;

    @Autowired
    public VendorService(VendorMapper mapper, VendorRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public VendorList getAllVendor() {
        List<VendorEntity> entityList = repo.findAll().stream().filter(
                vendorEntity -> vendorEntity.getDeletedAt() == null
        ).collect(Collectors.toList());

        VendorList list = mapper.mapVendorListFromVendorEntities(entityList);

        return list;
    }


    public VendorRes addVendor(VendorReq empReq) {
        VendorEntity empEntity = mapper.mapVendorEntityFromVendorReq(empReq);
        VendorEntity saved = repo.save(empEntity);

        return mapper.mapVendorResFromVendorEntity(saved);
    }

    public VendorRes getVendor(int id) {
        VendorEntity empEntity = repo.getById(id);

        if(empEntity.getDeletedAt() != null) {
            return new VendorRes();
        }

        return mapper.mapVendorResFromVendorEntity(empEntity);
    }

    public VendorRes updateVendor(int id, VendorReq empReq) {
        VendorEntity empEntity = mapper.mapVendorEntityFromVendorReq(id, empReq);
        VendorEntity saved = repo.save(empEntity);

        return mapper.mapVendorResFromVendorEntity(saved);
    }

    public void deleteVendor(int id) {
        VendorEntity empEntity = repo.getById(id);
        Date current = new Date();

        empEntity.setDeletedAt(current);

        repo.save(empEntity);
    }
}
