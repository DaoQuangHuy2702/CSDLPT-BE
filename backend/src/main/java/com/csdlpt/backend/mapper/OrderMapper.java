package com.csdlpt.backend.mapper;

import com.csdlpt.backend.entity.OrderEntity;
import com.csdlpt.backend.model.order.OrderList;
import com.csdlpt.backend.model.order.OrderReq;
import com.csdlpt.backend.model.order.OrderRes;
import com.csdlpt.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderMapper {
    private final OrderRepository repo;

    @Autowired
    public OrderMapper(OrderRepository repo) {
        this.repo = repo;
    }

    public OrderRes mapOrderResFromOrderEntity(OrderEntity from) {
        OrderRes to = new OrderRes();

        to.setId(from.getId());
        to.setCustomerId(from.getCustomerId());
        to.setProductDetailId(from.getProductDetailId());
        to.setStatus(from.getStatus());
        to.setCreatedAt(from.getCreatedAt());
        to.setUpdatedAt(from.getUpdatedAt());

        return to;
    }

    public OrderList mapOrderListFromOrderEntities(List<OrderEntity> from) {
        OrderList to = new OrderList();

        from.stream().forEach(order -> {
            to.add(mapOrderResFromOrderEntity(order));
        });

        return to;
    }

    public OrderEntity mapOrderEntityFromOrderReq(OrderReq from) {
        OrderEntity to = new OrderEntity();

        Date current = new Date();

        to.setCustomerId(from.getCustomerId());
        to.setProductDetailId(from.getProductDetailId());
        to.setStatus(from.getStatus());
        to.setCreatedAt(current);
        to.setUpdatedAt(current);

        return to;
    }

    public OrderEntity mapOrderEntityFromOrderReq(int id, OrderReq from) {
        OrderEntity to = repo.getById(id);
        Date current = new Date();

        to.setCustomerId(from.getCustomerId());
        to.setProductDetailId(from.getProductDetailId());
        to.setStatus(from.getStatus());
        to.setUpdatedAt(current);

        return to;
    }
}

