/**
 * Created on Nov 25, 2011
 */
package org.websandbox.springrest.web.restful.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.websandbox.springrest.domain.Customer;
import org.websandbox.springrest.domain.Customers;
import org.websandbox.springrest.service.CustomerService;

@Controller
@RequestMapping(value="/customer")
public class CustomerController {

	final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/listdata", method = RequestMethod.GET)
	@ResponseBody
	public Customers listData() {
		return new Customers(customerService.findAll());
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Customer findCustomerById(@PathVariable Long id) {		
		return customerService.findById(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public Customer create(@RequestBody @Valid Customer customer) {
		logger.info("Creating customer: " + customer);
		customerService.save(customer);
		logger.info("Customer created successfully with info: " + customer);
		return customer;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody Customer customer, @PathVariable Long id) {
		logger.info("Updating customer: " + customer);
		customerService.save(customer);
		logger.info("Customer updated successfully with info: " + customer);
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		logger.info("Deleting customer with id: " + id);
		Customer customer = customerService.findById(id);
		customerService.delete(customer);
		logger.info("Customer deleted successfully");
	}	
	
}
