package com.csdlpt.backend.api;

import com.csdlpt.backend.model.employee.EmployeeList;
import com.csdlpt.backend.model.employee.EmployeeReq;
import com.csdlpt.backend.model.employee.EmployeeRes;
import com.csdlpt.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("backend/v1")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/employees",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<EmployeeList> getAllEmployee() {
        EmployeeList response = service.getAllEmployee();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/branches/{id}/employees",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<EmployeeList> getAllEmployeeByBranchId(@PathVariable("id") int id) {
        EmployeeList response = service.getAllEmployeeByBranchId(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<EmployeeRes> addEmployee(@RequestBody EmployeeReq empReq) {
        EmployeeRes response = service.addEmployee(empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<EmployeeRes> getEmployee(@PathVariable("id") int id) {
        EmployeeRes response = service.getEmployee(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{id}",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<EmployeeRes> putEmployee(@PathVariable("id") int id, @RequestBody EmployeeReq empReq) {
        EmployeeRes response = service.updateEmployee(id, empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<EmployeeRes> deleteEmployee(@PathVariable("id") int id) {
        service.deleteEmployee(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
