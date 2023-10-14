package ro.salon.menu.customers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import ro.salon.common.entity.Customer;
import ro.salon.common.price.ReservationPrice;
import ro.salon.service.CustomerService;

@SuppressWarnings("serial")
public class Reservation extends JFrame {
	private String reservationPrice = "0";
	private CustomerService customerService = new CustomerService();
	private ReservationPrice price = new ReservationPrice();

	private JTextField fNameTextField;
	private JTextField lNameTextField;
	private JTextField phoneNumberTextField;
	private JLabel totalPrice;

	private JFrame frame;
	@SuppressWarnings("rawtypes")
	private JComboBox hairComboBox = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox eyeComboBox = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox manicureComboBox = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox pedicureComboBox = new JComboBox();
	private JButton reservationButton = new JButton();
	private JLabel hairRemovalLabel = new JLabel();
	private JLabel eyebrowsLabel = new JLabel();
	private JLabel manicureLabel = new JLabel();
	private JLabel phoneLabel = new JLabel();
	private JLabel lastNameLabel = new JLabel();
	private JLabel fNameLabel = new JLabel();
	private JLabel pedicureLabel = new JLabel();
	private JButton backButton = new JButton();

	private JComboBox<String> timeComboBox;
	private JDateChooser dateChooser;

	public Reservation() {
		createWindow();
		components();
		showComponents();
	}

	public void createWindow() {
		frame = new JFrame();
		frame.setTitle("APPOINTMENT");
		frame.setBounds(100, 100, 500, 500);
		frame.getContentPane().setBackground(Color.white);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setResizable(false);
	}

	public void showComponents() {
		frame.add(fNameLabel);
		frame.add(fNameTextField);
		frame.add(lastNameLabel);
		frame.add(lNameTextField);
		frame.add(phoneLabel);
		frame.add(phoneNumberTextField);
		frame.add(backButton);
		frame.add(reservationButton);
		frame.add(eyeComboBox);
		frame.add(eyebrowsLabel);
		frame.add(hairComboBox);
		frame.add(hairRemovalLabel);
		frame.add(manicureComboBox);
		frame.add(manicureLabel);
		frame.add(pedicureComboBox);
		frame.add(pedicureLabel);
		frame.add(totalPrice);
		frame.add(timeComboBox);
		frame.add(dateChooser);

	}

    private class DateChangeListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("date".equals(evt.getPropertyName())) {
                updateAvailableTimeSlots();
            }
        }
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void components() {
		getContentPane().setLayout(null);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(158, 255, 150, 30);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		getContentPane().add(dateChooser);
		dateChooser.addPropertyChangeListener("date", new DateChangeListener());


		timeComboBox = new JComboBox<>();
		timeComboBox.setBounds(158, 290, 150, 30);
		getContentPane().add(timeComboBox);

		fNameTextField = new JTextField();
		fNameTextField.setBounds(158, 35, 222, 19);
		getContentPane().add(fNameTextField);
		fNameTextField.setColumns(10);

		lNameTextField = new JTextField();
		lNameTextField.setBounds(158, 64, 222, 19);
		getContentPane().add(lNameTextField);
		lNameTextField.setColumns(10);

		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setBounds(158, 93, 222, 19);
		getContentPane().add(phoneNumberTextField);
		phoneNumberTextField.setColumns(10);

		hairComboBox = new JComboBox();
		hairComboBox.setModel(new DefaultComboBoxModel(new String[] { "NONE", "LEGS", "HANDS", "PUBIC", "LEGS AND HANDS",
				"LEGS AND PUBIC", "HANDS AND PUBIC", "ALL" }));
		hairComboBox.setBounds(158, 122, 190, 21);
		getContentPane().add(hairComboBox);

		eyeComboBox = new JComboBox();
		eyeComboBox.setModel(new DefaultComboBoxModel(new String[] { "NONE", "PAINT", "SHAPING", "TRIMMED",
				"PAINT AND SHAPING", "PAINT AND TRIMMED", "SHAPING AND TRIMMED", "ALL" }));
		eyeComboBox.setBounds(158, 153, 190, 21);
		getContentPane().add(eyeComboBox);

		manicureComboBox = new JComboBox();
		manicureComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "NONE", "PERMANENT", "SEMIPERMANENT", "SIMPLE MANICURE" }));
		manicureComboBox.setBounds(158, 184, 190, 21);
		getContentPane().add(manicureComboBox);

		pedicureComboBox = new JComboBox();
		pedicureComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "NONE", "PERMANENT", "SEMIPERMANENT", "SIMPLE PEDICURE" }));
		pedicureComboBox.setBounds(158, 218, 190, 21);
		getContentPane().add(pedicureComboBox);

		hairComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotalPrice();
			}
		});

		eyeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotalPrice();
			}
		});

		manicureComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotalPrice();
			}
		});

		pedicureComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotalPrice();
			}
		});

		reservationButton = new JButton("DONE");
		reservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				if (ee.getSource() == reservationButton) {

					Customer clientReservation = new Customer();

					LocalDate selectedDate = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					String selectedTime = timeComboBox.getSelectedItem().toString();;

					clientReservation.setFirstName(fNameTextField.getText());
					clientReservation.setLastName(lNameTextField.getText());
					clientReservation.setPhoneNumber(phoneNumberTextField.getText());

					clientReservation.setHairRemoval(hairComboBox.getSelectedItem().toString());
					float hair = price.hairRemovalPrice(hairComboBox.getSelectedItem().toString());

					clientReservation.setEyebrows(eyeComboBox.getSelectedItem().toString());
					float eyebrows = price.eyebrowsPrice(eyeComboBox.getSelectedItem().toString());

					clientReservation.setManicure(manicureComboBox.getSelectedItem().toString());
					float manicure = price.manicurePrice(manicureComboBox.getSelectedItem().toString());

					clientReservation.setPedicure(pedicureComboBox.getSelectedItem().toString());
					float pedicure = price.pedicurePrice(pedicureComboBox.getSelectedItem().toString());

					clientReservation.setReservationDate(selectedDate);
					clientReservation.setReservationHour(String.valueOf(selectedTime));

					clientReservation.setTotalPrice(price.getPrice(hair, eyebrows, manicure, pedicure));

					customerService.save(clientReservation);

					clearFields();
				}
			}
		});
		reservationButton.setBounds(140, 340, 85, 21);
		getContentPane().add(reservationButton);

		totalPrice = new JLabel("PRICE: " + String.valueOf(reservationPrice) + "$");
		totalPrice.setBounds(250, 340, 100, 20);
		getContentPane().add(totalPrice);

		fNameLabel = new JLabel("FIRST  NAME");
		fNameLabel.setBounds(10, 38, 85, 13);
		getContentPane().add(fNameLabel);

		lastNameLabel = new JLabel("LAST  NAME");
		lastNameLabel.setBounds(10, 67, 85, 13);
		getContentPane().add(lastNameLabel);

		phoneLabel = new JLabel("PHONE  NUMBER");
		phoneLabel.setBounds(10, 96, 130, 13);
		getContentPane().add(phoneLabel);

		hairRemovalLabel = new JLabel("HAIR  REMOVAL");
		hairRemovalLabel.setBounds(10, 126, 130, 13);
		getContentPane().add(hairRemovalLabel);

		eyebrowsLabel = new JLabel("EYEBROWS");
		eyebrowsLabel.setBounds(10, 157, 130, 13);
		getContentPane().add(eyebrowsLabel);

		manicureLabel = new JLabel("MANICURE");
		manicureLabel.setBounds(10, 188, 130, 13);
		getContentPane().add(manicureLabel);

		pedicureLabel = new JLabel("PEDICURE");
		pedicureLabel.setBounds(10, 222, 130, 13);
		getContentPane().add(pedicureLabel);

		backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		backButton.setBounds(10, 340, 85, 21);
		getContentPane().add(backButton);

	}

	private void clearFields() {
		fNameTextField.setText("");
		lNameTextField.setText("");
		phoneNumberTextField.setText("");

	}

	private void updateTotalPrice() {

		float hair = price.hairRemovalPrice(hairComboBox.getSelectedItem().toString());
		float eyebrows = price.eyebrowsPrice(eyeComboBox.getSelectedItem().toString());
		float manicure = price.manicurePrice(manicureComboBox.getSelectedItem().toString());
		float pedicure = price.pedicurePrice(pedicureComboBox.getSelectedItem().toString());

		reservationPrice = String.valueOf(hair + eyebrows + manicure + pedicure) + "$";

		totalPrice.setText("PRICE: " + String.valueOf(reservationPrice));
	}


    private void updateAvailableTimeSlots() {
        LocalDate selectedDate = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Customer> reservations =customerService.getUnavailableHours(selectedDate);
        List<String> availableTimeSlots = new ArrayList<>();

        LocalTime startTime = LocalTime.of(9, 0); // Salon opening time
        LocalTime endTime = LocalTime.of(21, 0); // Salon closing time

        // Generate available time slots within working hours
        while (startTime.isBefore(endTime)) {
            String timeSlot = startTime.toString();
            if (!reservations.contains(timeSlot)) {
                availableTimeSlots.add(timeSlot);
            }
            startTime = startTime.plusMinutes(60); // Adjust this according to your desired time slot interval
        }

      
        DefaultComboBoxModel<String> timeComboBoxModel = new DefaultComboBoxModel<>(availableTimeSlots.toArray(new String[0]));
        timeComboBox.setModel(timeComboBoxModel);
    }





}
