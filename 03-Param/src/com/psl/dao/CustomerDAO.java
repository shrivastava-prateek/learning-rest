package com.psl.dao;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.psl.bean.Customer;
import com.psl.bean.Customers;
import com.psl.service.CustomerService;

public class CustomerDAO {

	private static CopyOnWriteArrayList<Customer> customers = new CopyOnWriteArrayList<Customer>();
	
	static{
		for (int count = 1; count <=100; count++) {
			customers.add(new Customer(count,"fisrtName"+count,"lastName"+count));
		}
	}
	
	/*public Customer getCustomerById(int id){
		Customer searchCustomer = new Customer();
		searchCustomer.setCustomerId(id);
		return customers.get(customers.indexOf(searchCustomer));
	}*/
	
	/*
	public Customer getCustomerById(int id){
		Customer searchCustomer = new Customer();
		searchCustomer.setCustomerId(id);
		int index;
		index=customers.indexOf(searchCustomer);
		if(index==-1){
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).build());
		}
		return customers.get(index);
	}*/
	

	public Customer getCustomerById(int id){
		Customer searchCustomer = new Customer();
		searchCustomer.setCustomerId(id);
		int index;
		index=customers.indexOf(searchCustomer);
		if(index==-1){
			throw new CustomerNotFoundException();
		}
		return customers.get(index);
	}

	public void updateCustomer(Customer customer) {

		Customer searchCustomer = getCustomerById(customer.getCustomerId());
		searchCustomer.setFirstName(customer.getFirstName());
		searchCustomer.setLastName(customer.getLastName());
	}
	
	public void save(Customer customer){
		customers.add(customer);
		
	}
	 
	public void delete(Customer customer){
		customers.remove(customer);
		System.out.println(customers);
	}

	public Customers getCustomers(int start, int size) {
		Customers _customers = new Customers();
		_customers.setCustomers(customers.subList(start, start+size));
		return _customers;
	}
}
