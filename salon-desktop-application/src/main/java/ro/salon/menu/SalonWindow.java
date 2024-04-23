package ro.salon.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ro.salon.menu.customers.Reservation;
import ro.salon.menu.customers.ReservationList;
import ro.salon.menu.employees.EmployeeInsert;

public class SalonWindow implements ActionListener {

	private JButton reservationButton = new JButton("RESERVATION");
	private JButton employeeButton = new JButton("EMPLOYEE");
	private JButton reservationListButton = new JButton("RESERVATION LIST");
	private JFrame frame;
	private final JButton exitButton = new JButton("EXIT");
	
	public SalonWindow() {

		createWindow();
		setLocationAndSize();
		addComponentsToFrame();
		actionEvent();

	}

	public void createWindow() {
		frame = new JFrame();
		frame.setTitle("SALON COSMOS");
		frame.setBounds(100, 100, 400, 400);
		frame.getContentPane().setBackground(Color.white);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(true);

	}

	public void setLocationAndSize() {
		
		reservationButton.setBounds(102, 132, 170, 35);
		employeeButton.setBounds(102, 68, 170, 35);
		reservationListButton.setBounds(102, 193, 170, 35);
		exitButton.setBounds(10, 318, 100, 35);

	}

	public void addComponentsToFrame() {
		frame.getContentPane().add(reservationButton);
		frame.getContentPane().add(employeeButton);
		frame.getContentPane().add(reservationListButton);
		frame.getContentPane().add(exitButton);
		
		
	}

	public void actionEvent() {
		reservationButton.addActionListener(this);
		employeeButton.addActionListener(this);
		reservationListButton.addActionListener(this);
		exitButton.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==reservationButton) {
			new Reservation();
		}
		if(e.getSource()==employeeButton) {
			new EmployeeInsert();
		}
		if(e.getSource()==reservationListButton) {
			new ReservationList();
		}
		if(e.getSource()==exitButton) {
			System.exit(0);
		}
		
	}
}
