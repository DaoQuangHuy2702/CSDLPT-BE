package com.csdlpt.backend.service;

import com.csdlpt.backend.entity.OrderEntity;
import com.csdlpt.backend.mapper.OrderMapper;
import com.csdlpt.backend.model.order.OrderList;
import com.csdlpt.backend.model.order.OrderReq;
import com.csdlpt.backend.model.order.OrderRes;
import com.csdlpt.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderMapper mapper;
    private final OrderRepository repo;

    @Autowired
    public OrderService(OrderMapper mapper, OrderRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public OrderList getAllOrder() {
        List<OrderEntity> entityList = repo.findAll().stream().filter(
                orderEntity -> orderEntity.getDeletedAt() == null
        ).collect(Collectors.toList());

        OrderList list = mapper.mapOrderListFromOrderEntities(entityList);

        return list;
    }

    public OrderRes addOrder(OrderReq empReq) {
        OrderEntity empEntity = mapper.mapOrderEntityFromOrderReq(empReq);
        OrderEntity saved = repo.save(empEntity);

        return mapper.mapOrderResFromOrderEntity(saved);
    }

    public OrderRes getOrder(int id) {
        OrderEntity empEntity = repo.getById(id);

        if(empEntity.getDeletedAt() != null) {
            return new OrderRes();
        }

        return mapper.mapOrderResFromOrderEntity(empEntity);
    }

    public OrderRes updateOrder(int id, OrderReq empReq) {
        OrderEntity empEntity = mapper.mapOrderEntityFromOrderReq(id, empReq);
        OrderEntity saved = repo.save(empEntity);

        return mapper.mapOrderResFromOrderEntity(saved);
    }

    public void deleteOrder(int id) {
        OrderEntity empEntity = repo.getById(id);
        Date current = new Date();

        empEntity.setDeletedAt(current);

        repo.save(empEntity);
    }
}
