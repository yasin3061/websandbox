/**
 * Created on Nov 25, 2011
 */
package org.websandbox.springrest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.websandbox.springrest.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	public List<Customer> findByFirstName(String firstName);
	
}
