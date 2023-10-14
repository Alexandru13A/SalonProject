package ro.salon.salonwebapplication.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.salon.common.entity.Customer;
import ro.salon.common.price.ReservationPrice;

@Controller
public class CustomerController {

  @Autowired
  private CustomerService customerService;
  private ReservationPrice price = new ReservationPrice();

  @GetMapping("")
  public String firstPage() {
    return "index";
  }

  @GetMapping("/create")
  public String createCustomer(Model model) {
    Customer customer = new Customer();
    model.addAttribute("customer", customer);
    model.addAttribute("pageTitle", "Make Your Appointment");
    return "customer/customer_form";
  }

  @PostMapping("/save")
  public String saveCustomer(Customer customer, RedirectAttributes redirectAttributes) {

    float hair = price.hairRemovalPrice(customer.getHairRemoval());
    float eyebrows = price.eyebrowsPrice(customer.getEyebrows());
    float manicure = price.manicurePrice(customer.getManicure());
    float pedicure = price.pedicurePrice(customer.getPedicure());

    float total = price.getPrice(hair, eyebrows, manicure, pedicure);

    String date = String.valueOf(customer.getReservationDate());
    String hour = customer.getReservationHour();
    String fullName = customer.getFirstName() + " " + customer.getLastName();

    customer.setTotalPrice(total);
    customerService.save(customer);

   redirectAttributes.addFlashAttribute("totalPrice", total);
   redirectAttributes.addFlashAttribute("hour", hour);
   redirectAttributes.addFlashAttribute("date", date);
   redirectAttributes.addFlashAttribute("fullName", fullName);

    return "redirect:/appointment_information";
  }

  @GetMapping("/appointment_information")
  public String information(){

    return "customer/customer_success";
  }

@GetMapping("/about")
public String aboutUs(){

  return "about";
}
@GetMapping("/contact")
public String contact(Model model){
  model.addAttribute("email", "salon@gmail.com");
  model.addAttribute("phone", "0755-432-123");
  return "contact";
}
@GetMapping("/services")
public String services(){
 
  return "services";
}


}
