package com.csdlpt.backend.service;

import com.csdlpt.backend.entity.CustomerEntity;
import com.csdlpt.backend.mapper.CustomerMapper;
import com.csdlpt.backend.model.customer.CustomerList;
import com.csdlpt.backend.model.customer.CustomerReq;
import com.csdlpt.backend.model.customer.CustomerRes;
import com.csdlpt.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerMapper mapper;
    private final CustomerRepository repo;

    @Autowired
    public CustomerService(CustomerMapper mapper, CustomerRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public CustomerList getAllCustomer() {
        List<CustomerEntity> entityList = repo.findAll().stream().filter(
                customerEntity -> customerEntity.getDeletedAt() == null
        ).collect(Collectors.toList());

        CustomerList list = mapper.mapCustomerListFromCustomerEntities(entityList);

        return list;
    }

    public CustomerRes addCustomer(CustomerReq empReq) {
        CustomerEntity empEntity = mapper.mapCustomerEntityFromCustomerReq(empReq);
        CustomerEntity saved = repo.save(empEntity);

        return mapper.mapCustomerResFromCustomerEntity(saved);
    }

    public CustomerRes getCustomer(int id) {
        CustomerEntity empEntity = repo.getById(id);

        if(empEntity.getDeletedAt() != null) {
            return new CustomerRes();
        }

        return mapper.mapCustomerResFromCustomerEntity(empEntity);
    }

    public CustomerRes updateCustomer(int id, CustomerReq empReq) {
        CustomerEntity empEntity = mapper.mapCustomerEntityFromCustomerReq(id, empReq);
        CustomerEntity saved = repo.save(empEntity);

        return mapper.mapCustomerResFromCustomerEntity(saved);
    }

    public void deleteCustomer(int id) {
        CustomerEntity empEntity = repo.getById(id);
        Date current = new Date();

        empEntity.setDeletedAt(current);

        repo.save(empEntity);
    }
}
