package com.csdlpt.backend.mapper;

import com.csdlpt.backend.entity.FileEntity;
import com.csdlpt.backend.model.file.FileList;
import com.csdlpt.backend.model.file.FileReq;
import com.csdlpt.backend.model.file.FileRes;
import com.csdlpt.backend.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FileMapper {
    private final FileRepository repo;

    @Autowired
    public FileMapper(FileRepository repo) {
        this.repo = repo;
    }

    public FileRes mapFileResFromFileEntity(FileEntity from) {
        FileRes to = new FileRes();

        to.setId(from.getId());
        to.setProductId(from.getProductId());

        return to;
    }

    public FileList mapFileListFromFileEntities(List<FileEntity> from) {
        FileList to = new FileList();

        from.stream().forEach(file -> {
            to.add(mapFileResFromFileEntity(file));
        });

        return to;
    }

    public FileEntity mapFileEntityFromFileReq(FileReq from) {
        FileEntity to = new FileEntity();

        Date current = new Date();

        to.setProductId(from.getProductId());

        return to;
    }

    public FileEntity mapFileEntityFromFileReq(int id, FileReq from) {
        FileEntity to = repo.getById(id);
        Date current = new Date();

        to.setProductId(from.getProductId());

        return to;
    }
}

