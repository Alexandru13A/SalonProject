package ro.salon.salonwebapplication.customer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ro.salon.common.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  @Query("SELECT c.reservationHour FROM Customer c WHERE c.reservationDate = ?1 ")
  List<String> hours(LocalDate date);

}
