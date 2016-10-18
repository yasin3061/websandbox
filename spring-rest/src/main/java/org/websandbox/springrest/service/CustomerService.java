/**
 * Created on Nov 25, 2011
 */
package org.websandbox.springrest.service;

import java.util.List;

import org.websandbox.springrest.domain.Customer;

public interface CustomerService {

	public List<Customer> findAll();
	
	public List<Customer> findByFirstName(String firstName);
	
	public Customer findById(Long id);
	
	public Customer save(Customer customer);
	
	public void delete(Customer customer);
	
}
