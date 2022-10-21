package com.csdlpt.backend.api;

import com.csdlpt.backend.model.vendor.VendorList;
import com.csdlpt.backend.model.vendor.VendorReq;
import com.csdlpt.backend.model.vendor.VendorRes;
import com.csdlpt.backend.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("backend/v1")
public class VendorController {
    private final VendorService service;

    @Autowired
    public VendorController(VendorService service) {
        this.service = service;
    }

    @RequestMapping(value = "/vendors",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<VendorList> getAllVendor() {
        VendorList response = service.getAllVendor();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/vendors",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<VendorRes> addVendor(@RequestBody VendorReq empReq) {
        VendorRes response = service.addVendor(empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/vendors/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<VendorRes> getVendor(@PathVariable("id") int id) {
        VendorRes response = service.getVendor(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/vendors/{id}",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<VendorRes> putVendor(@PathVariable("id") int id, @RequestBody VendorReq empReq) {
        VendorRes response = service.updateVendor(id, empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/vendors/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<VendorRes> deleteVendor(@PathVariable("id") int id) {
        service.deleteVendor(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
