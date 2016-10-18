/**
 * Created on Nov 25, 2011
 */
package org.websandbox.springrest.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.websandbox.springrest.domain.Customer;
import org.websandbox.springrest.repository.CustomerRepository;
import org.websandbox.springrest.service.CustomerService;

import com.google.common.collect.Lists;

@Service("customerService")
@Repository
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional(readOnly=true)
	public List<Customer> findAll() {
		return Lists.newArrayList(customerRepository.findAll());
	}

	@Transactional(readOnly=true)
	public List<Customer> findByFirstName(String firstName) {
		return customerRepository.findByFirstName(firstName);
	}

	@Transactional(readOnly=true)	
	public Customer findById(Long id) {
		return customerRepository.findOne(id);
	}

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}

}
