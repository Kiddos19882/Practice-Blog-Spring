package com.example.blog.controller;

import com.example.blog.models.Customer;
import com.example.blog.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {
    final
    CustomerServices customerServices;

    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    @RequestMapping("/customer")
    public ModelAndView listCustomer() {
        List<Customer> listCustomer = customerServices.listAll();
        ModelAndView mav = new ModelAndView("customer");
        mav.addObject("listCustomer", listCustomer);
        return mav;
    }

    @GetMapping("/new-customer")
    public String newCustomer(Map<String, Object> model) {
        Customer customer = new Customer();
        model.put("customer", customer);
        return "new-customer";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerServices.save(customer);
        return "redirect:/customer";
    }

    @RequestMapping("/edit")
    public ModelAndView editCustomerForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("edit-customer");
        Customer customer = customerServices.get(id);
        mav.addObject("customer", customer);
        return mav;
    }


    @RequestMapping("{id}/save")
    public String updateCustomer(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String email, @RequestParam String address) {
        Customer customer = customerServices.get(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        customerServices.save(customer);
        return "redirect:/customer";
    }

    @RequestMapping("/delete")
    public String deleteCustomerForm(@RequestParam long id) {
        customerServices.delete(id);
        return "redirect:/customer";
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Customer> result = customerServices.search(keyword);
        ModelAndView mav = new ModelAndView();
        mav.addObject("result", result);
        return mav;
    }
}
