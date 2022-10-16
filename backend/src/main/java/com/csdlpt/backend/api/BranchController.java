package com.csdlpt.backend.api;

import com.csdlpt.backend.model.branch.BranchList;
import com.csdlpt.backend.model.branch.BranchReq;
import com.csdlpt.backend.model.branch.BranchRes;
import com.csdlpt.backend.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("backend/v1")
public class BranchController {
    private final BranchService service;

    @Autowired
    public BranchController(BranchService service) {
        this.service = service;
    }

    @RequestMapping(value = "/branches",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<BranchList> getAllBranch() {
        BranchList response = service.getAllBranch();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/branches",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<BranchRes> addBranch(@RequestBody BranchReq branchReq) {
        BranchRes response = service.addBranch(branchReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/branches/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<BranchRes> getBranch(@PathVariable("id") int id) {
        BranchRes response = service.getBranch(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/branches/{id}",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<BranchRes> putDepartment(@PathVariable("id") int id,
                                                   @RequestBody BranchReq branchReq) {
        BranchRes response = service.updateBranch(id, branchReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/branches/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<BranchRes> deleteBranch(@PathVariable("id") int id) {
        service.deleteBranch(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
