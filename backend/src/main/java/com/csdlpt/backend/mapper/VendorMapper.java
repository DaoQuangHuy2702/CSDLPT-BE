package com.csdlpt.backend.mapper;

import com.csdlpt.backend.entity.VendorEntity;
import com.csdlpt.backend.model.vendor.VendorList;
import com.csdlpt.backend.model.vendor.VendorReq;
import com.csdlpt.backend.model.vendor.VendorRes;
import com.csdlpt.backend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VendorMapper {
    private final VendorRepository repo;

    @Autowired
    public VendorMapper(VendorRepository repo) {
        this.repo = repo;
    }

    public VendorRes mapVendorResFromVendorEntity(VendorEntity from) {
        VendorRes to = new VendorRes();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setPhone(from.getPhone());
        to.setCreatedAt(from.getCreatedAt());
        to.setUpdatedAt(from.getUpdatedAt());

        return to;
    }

    public VendorList mapVendorListFromVendorEntities(List<VendorEntity> from) {
        VendorList to = new VendorList();

        from.stream().forEach(vendor -> {
            to.add(mapVendorResFromVendorEntity(vendor));
        });

        return to;
    }

    public VendorEntity mapVendorEntityFromVendorReq(VendorReq from) {
        VendorEntity to = new VendorEntity();

        Date current = new Date();

        to.setName(from.getName());
        to.setPhone(from.getPhone());
        to.setCreatedAt(current);
        to.setUpdatedAt(current);

        return to;
    }

    public VendorEntity mapVendorEntityFromVendorReq(int id, VendorReq from) {
        VendorEntity to = repo.getById(id);
        Date current = new Date();

        to.setName(from.getName());
        to.setPhone(from.getPhone());
        to.setUpdatedAt(current);

        return to;
    }
}

