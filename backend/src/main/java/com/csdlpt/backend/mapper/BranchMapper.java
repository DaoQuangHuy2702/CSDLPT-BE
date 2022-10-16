package com.csdlpt.backend.mapper;

import com.csdlpt.backend.entity.BranchEntity;
import com.csdlpt.backend.model.branch.BranchList;
import com.csdlpt.backend.model.branch.BranchReq;
import com.csdlpt.backend.model.branch.BranchRes;
import com.csdlpt.backend.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BranchMapper {
    private final BranchRepository repo;

    @Autowired
    public BranchMapper(BranchRepository repo) {
        this.repo = repo;
    }

    public BranchRes mapBranchResFromBranchEntity(BranchEntity from) {
        BranchRes to = new BranchRes();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setAddress(from.getAddress());
        to.setPhone(from.getPhone());
        to.setCreatedAt(from.getCreatedAt());
        to.setUpdatedAt(from.getUpdatedAt());

        return to;
    }

    public BranchList mapBranchListFromBranchEntities(List<BranchEntity> from) {
        BranchList to = new BranchList();

        from.stream().forEach(department -> {
            to.add(mapBranchResFromBranchEntity(department));
        });

        return to;
    }

    public BranchEntity mapBranchEntityFromBranchReq(BranchReq from) {
        BranchEntity to = new BranchEntity();

        Date current = new Date();

        to.setName(from.getName());
        to.setAddress(from.getAddress());
        to.setPhone(from.getPhone());
        to.setCreatedAt(current);
        to.setUpdatedAt(current);

        return to;
    }

    public BranchEntity mapBranchEntityFromBranchReq(int id, BranchReq from) {
        BranchEntity to = repo.getById(id);

        Date current = new Date();

        to.setName(from.getName());
        to.setAddress(from.getAddress());
        to.setPhone(from.getPhone());
        to.setUpdatedAt(current);

        return to;
    }
}
