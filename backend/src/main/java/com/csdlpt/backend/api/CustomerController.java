package com.csdlpt.backend.api;

import com.csdlpt.backend.model.customer.CustomerList;
import com.csdlpt.backend.model.customer.CustomerReq;
import com.csdlpt.backend.model.customer.CustomerRes;
import com.csdlpt.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("backend/v1")
public class CustomerController {
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/customers",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<CustomerList> getAllCustomer() {
        CustomerList response = service.getAllCustomer();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<CustomerRes> addCustomer(@RequestBody CustomerReq empReq) {
        CustomerRes response = service.addCustomer(empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<CustomerRes> getCustomer(@PathVariable("id") int id) {
        CustomerRes response = service.getCustomer(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{id}",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<CustomerRes> putCustomer(@PathVariable("id") int id, @RequestBody CustomerReq empReq) {
        CustomerRes response = service.updateCustomer(id, empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<CustomerRes> deleteCustomer(@PathVariable("id") int id) {
        service.deleteCustomer(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
