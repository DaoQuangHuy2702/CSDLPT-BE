package com.csdlpt.backend.service;

import com.csdlpt.backend.entity.FileEntity;
import com.csdlpt.backend.mapper.FileMapper;
import com.csdlpt.backend.model.file.FileList;
import com.csdlpt.backend.model.file.FileReq;
import com.csdlpt.backend.model.file.FileRes;
import com.csdlpt.backend.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    private final FileMapper mapper;
    private final FileRepository repo;

    @Autowired
    public FileService(FileMapper mapper, FileRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public FileList getAllFile() {
        List<FileEntity> entityList = repo.findAll();

        return mapper.mapFileListFromFileEntities(entityList);
    }

    public FileRes addFile(FileReq empReq) {
        FileEntity empEntity = mapper.mapFileEntityFromFileReq(empReq);
        FileEntity saved = repo.save(empEntity);

        return mapper.mapFileResFromFileEntity(saved);
    }

    public FileRes getFile(int id) {
        FileEntity empEntity = repo.getById(id);

        return mapper.mapFileResFromFileEntity(empEntity);
    }

    public FileRes updateFile(int id, FileReq empReq) {
        FileEntity empEntity = mapper.mapFileEntityFromFileReq(id, empReq);
        FileEntity saved = repo.save(empEntity);

        return mapper.mapFileResFromFileEntity(saved);
    }

    public void deleteFile(int id) {
        FileEntity empEntity = repo.getById(id);

        repo.save(empEntity);
    }
}
