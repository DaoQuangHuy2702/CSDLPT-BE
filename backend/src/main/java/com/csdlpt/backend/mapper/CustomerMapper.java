package com.csdlpt.backend.mapper;

import com.csdlpt.backend.entity.CustomerEntity;
import com.csdlpt.backend.model.customer.CustomerList;
import com.csdlpt.backend.model.customer.CustomerReq;
import com.csdlpt.backend.model.customer.CustomerRes;
import com.csdlpt.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerMapper {
    private final CustomerRepository repo;

    @Autowired
    public CustomerMapper(CustomerRepository repo) {
        this.repo = repo;
    }

    public CustomerRes mapCustomerResFromCustomerEntity(CustomerEntity from) {
        CustomerRes to = new CustomerRes();

        to.setId(from.getId());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setPhone(from.getPhone());
        to.setAddress(from.getAddress());
        to.setCreatedAt(from.getCreatedAt());
        to.setUpdatedAt(from.getUpdatedAt());

        return to;
    }

    public CustomerList mapCustomerListFromCustomerEntities(List<CustomerEntity> from) {
        CustomerList to = new CustomerList();

        from.stream().forEach(customer -> {
            to.add(mapCustomerResFromCustomerEntity(customer));
        });

        return to;
    }

    public CustomerEntity mapCustomerEntityFromCustomerReq(CustomerReq from) {
        CustomerEntity to = new CustomerEntity();

        Date current = new Date();

        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setPhone(from.getPhone());
        to.setAddress(from.getAddress());
        to.setCreatedAt(current);
        to.setUpdatedAt(current);

        return to;
    }

    public CustomerEntity mapCustomerEntityFromCustomerReq(int id, CustomerReq from) {
        CustomerEntity to = repo.getById(id);
        Date current = new Date();

        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setPhone(from.getPhone());
        to.setAddress(from.getAddress());
        to.setUpdatedAt(current);

        return to;
    }
}

