package com.csdlpt.backend.api;

import com.csdlpt.backend.model.file.FileList;
import com.csdlpt.backend.model.file.FileReq;
import com.csdlpt.backend.model.file.FileRes;
import com.csdlpt.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("backend/v1")
public class FileController {
    private final FileService service;

    @Autowired
    public FileController(FileService service) {
        this.service = service;
    }

    @RequestMapping(value = "/files",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<FileList> getAllFile() {
        FileList response = service.getAllFile();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/files",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<FileRes> addFile(@RequestBody FileReq empReq) {
        FileRes response = service.addFile(empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/files/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<FileRes> getFile(@PathVariable("id") int id) {
        FileRes response = service.getFile(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/files/{id}",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<FileRes> putFile(@PathVariable("id") int id, @RequestBody FileReq empReq) {
        FileRes response = service.updateFile(id, empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/files/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<FileRes> deleteFile(@PathVariable("id") int id) {
        service.deleteFile(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
