/**
 * Created on Nov 29, 2011
 */
package org.websandbox.springrest.domain;

import java.io.Serializable;
import java.util.List;

public class Customers implements Serializable {

	private static final long serialVersionUID = -7841176164574382148L;
	private List<Customer> customers;

	public Customers() {
	}
	
	public Customers(List<Customer> customer) {
		this.customers = customer;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	 
}
