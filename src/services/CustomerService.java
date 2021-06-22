package services;

import exceptions.CustomerNotFoundException;
import exceptions.DuplicateEntryException;
import exceptions.InvalidInputFormatException;
import models.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    public static Map<String,Customer> customers = new HashMap<String ,Customer>();

    /**
     * This method will interact with the customer
     * @ email provided by the customer
     * @param firstName provided by the customer
     * @param lastName provided by the customer
     */
    public static void addCustomer(String email, String firstName, String lastName) {
        if(!customers.containsKey(email)){
            try{
                customers.put(email,new Customer(firstName,lastName,email));
                System.out.println("User added.");
            }
            catch (InvalidInputFormatException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("User already exists.");
        }
    }

    public static Customer getCustomer(String customerEmail) throws CustomerNotFoundException{
        return customers.get(customerEmail);
    }

    /**
     * This method will interact only with admin
     * @return the state of all the customers
     */
    public static Collection<Customer> getAllCustomers(){
        if(customers.isEmpty()){
            System.out.println("There is no customers available.");
        }
        return customers.values();
    }
}
