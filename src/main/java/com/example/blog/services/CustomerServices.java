package com.example.blog.services;

import com.example.blog.models.Customer;
import com.example.blog.repo.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public record CustomerServices(CustomerRepository repo) {

    public void save(Customer customer) {
        repo.save(customer);
    }

    public List<Customer> listAll() {
        return (List<Customer>) repo.findAll();
    }

    public Customer get(Long id) {
        return repo.findById(id).orElse(null);
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Customer> search(String keyword) {
        return repo.search(keyword);
    }
}
