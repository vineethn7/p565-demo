package edu.iu.p565.customerservice.repository;

import edu.iu.p565.customerservice.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import java.lang.IllegalStateException;

@Repository
public class CustomerRepository {

    private List<Customer> customersList = new ArrayList<>();

    public List<Customer> findAllCustomers(){
        return customersList;
    }

    public int create(Customer customer){
        int customerID = customersList.size()+1;
        customer.setCustomerID(customerID);
        customersList.add(customer);
        return customerID;
    }
    private Customer getCustomerById(int id){
        return customersList.stream().filter(x  -> x.getCustomerID() == id).findAny().orElse(null);
    }
    public void update(Customer customer, int customerID){
        Customer existingCustomer = getCustomerById(customerID);
        if(existingCustomer!=null) {
            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setEmail(customer.getEmail());
        } else{
            throw new IllegalStateException("Cannot update this customer as id is invalid");
        }
    }

    public void delete(int customerID){
        Customer existingCustomer = getCustomerById(customerID);
        if(existingCustomer!=null){
            customersList.remove(existingCustomer);
        }else{
            throw new IllegalStateException("Cannot delete this customer as id is invalid");
        }
    }
}
