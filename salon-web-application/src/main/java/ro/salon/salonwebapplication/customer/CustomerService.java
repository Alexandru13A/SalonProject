package ro.salon.salonwebapplication.customer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.salon.common.entity.Customer;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public void save(Customer customer) {
    customerRepository.save(customer);
  }

  public List<String> getHours(LocalDate date){
    return customerRepository.hours(date);
  }
 

}
