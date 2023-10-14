package ro.salon.common.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "hair_removal")
	private String hairRemoval;
	@Column(name = "eyebrows")
	private String eyebrows;
	@Column(name = "manicure")
	private String manicure;
	@Column(name = "pedicure")
	private String pedicure;
	@Column(name = "reservation_date")
	private LocalDate reservationDate;
	@Column(name = "reservation_hour")
	private String reservationHour;
	@Column(name = "total_price")
	private float totalPrice;


	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", firstName='" + getFirstName() + "'" +
			", lastName='" + getLastName() + "'" +
			", phoneNumber='" + getPhoneNumber() + "'" +
			", hairRemoval='" + getHairRemoval() + "'" +
			", eyebrows='" + getEyebrows() + "'" +
			", manicure='" + getManicure() + "'" +
			", pedicure='" + getPedicure() + "'" +
			", reservationDate='" + getReservationDate() + "'" +
			", reservationHour='" + getReservationHour() + "'" +
			", totalPrice='" + getTotalPrice() + "'" +
			"}";
	}


}
