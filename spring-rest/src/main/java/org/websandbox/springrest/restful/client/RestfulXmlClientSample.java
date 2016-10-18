/**
 * Created on Nov 29, 2011
 */
package org.websandbox.springrest.restful.client;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;
import org.websandbox.springrest.domain.Customer;
import org.websandbox.springrest.domain.Customers;

public class RestfulXmlClientSample {

	private static final String URL_GET_ALL_CUSTOMERS = "http://localhost:8080/spring-rest/restful/customer/listdata";
	private static final String URL_GET_CUSTOMER_BY_ID = "http://localhost:8080/spring-rest/restful/customer/{id}";
	private static final String URL_CREATE_CUSTOMER = "http://localhost:8080/spring-rest/restful/customer/";
	private static final String URL_UPDATE_CUSTOMER = "http://localhost:8080/spring-rest/restful/customer/{id}";
	private static final String URL_DELETE_CUSTOMER = "http://localhost:8080/spring-rest/restful/customer/{id}";	

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:restful-client-xml-app-context.xml");
		ctx.refresh();
		
		Customers customers;
		Customer customer;
		
		RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);
		
		// Test retrieve all customers
		System.out.println("Testing retrieve all customers:");
		customers = restTemplate.getForObject(URL_GET_ALL_CUSTOMERS, Customers.class);
		listCustomers(customers);
		
		// Test retrieve customer by id
		System.out.println("Testing retrieve a customer by id :");
		customer = restTemplate.getForObject(URL_GET_CUSTOMER_BY_ID, Customer.class, 1);
		System.out.println(customer);
		System.out.println("");
		
		// Test update customer
		customer = restTemplate.getForObject(URL_UPDATE_CUSTOMER, Customer.class, 1);
		customer.setFirstName("John Doe");
		System.out.println("Testing update customer by id :");
		restTemplate.put(URL_UPDATE_CUSTOMER, customer, 1);
		System.out.println("Customer update successfully: " + customer);
		System.out.println("");	
		
		// Testing delete customer
		restTemplate.delete(URL_DELETE_CUSTOMER, 1);
		System.out.println("Testing delete customer by id :");
        customers = restTemplate.getForObject(URL_GET_ALL_CUSTOMERS, Customers.class);
        listCustomers(customers);
        
		// Testing create customer
        System.out.println("Testing create customer :");
		Customer customerNew = new Customer();
		customerNew.setFirstName("Jimmy");
		customerNew.setLastName("Danet");
		customerNew = restTemplate.postForObject(URL_CREATE_CUSTOMER, customerNew, Customer.class);
		System.out.println("Customer created successfully: " + customerNew);       

	}
	
	private static void listCustomers(Customers customers) {
		for (Customer customer: customers.getCustomers()) {
			System.out.println(customer);
		}	
		System.out.println("");
	}

}
