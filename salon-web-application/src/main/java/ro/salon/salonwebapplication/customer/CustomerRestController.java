package ro.salon.salonwebapplication.customer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

  private CustomerService customerService;

  public CustomerRestController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/unavailable_hours")
  public ResponseEntity<List<String>> unavailableHours(@RequestParam String date) {

    LocalDate selectedDate = LocalDate.parse(date);

    List<String> unavailableHours = customerService.getHours(selectedDate);

    return ResponseEntity.ok(unavailableHours);

  }

}
