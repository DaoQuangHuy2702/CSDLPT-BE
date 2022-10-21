package com.csdlpt.backend.api;

import com.csdlpt.backend.model.order.OrderList;
import com.csdlpt.backend.model.order.OrderReq;
import com.csdlpt.backend.model.order.OrderRes;
import com.csdlpt.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("backend/v1")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @RequestMapping(value = "/orders",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<OrderList> getAllOrder() {
        OrderList response = service.getAllOrder();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<OrderRes> addOrder(@RequestBody OrderReq empReq) {
        OrderRes response = service.addOrder(empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<OrderRes> getOrder(@PathVariable("id") int id) {
        OrderRes response = service.getOrder(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{id}",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<OrderRes> putOrder(@PathVariable("id") int id, @RequestBody OrderReq empReq) {
        OrderRes response = service.updateOrder(id, empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<OrderRes> deleteOrder(@PathVariable("id") int id) {
        service.deleteOrder(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
