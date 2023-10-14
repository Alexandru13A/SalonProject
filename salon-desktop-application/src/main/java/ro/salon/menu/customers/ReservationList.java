package ro.salon.menu.customers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;

import ro.salon.common.entity.Customer;
import ro.salon.service.CustomerService;

public class ReservationList extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTable table = new JTable(dtm);

	private List<Customer> result = new ArrayList<Customer>();
	private CustomerService customerService = new CustomerService();

	public ReservationList() {
		getContentPane().setForeground(Color.DARK_GRAY);
		setBackground(Color.WHITE);
		setForeground(new Color(64, 64, 64));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		showClients();

		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComponent comp = (JComponent) arg0.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		backButton.setBounds(1110, 570, 122, 35);
		getContentPane().add(backButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1240, 500);
		getContentPane().add(scrollPane);

		table = new JTable(dtm);
		scrollPane.setViewportView(table);

		JButton loadClients = new JButton("LOAD RESERVATION");
		loadClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
				showClients();
			}
		});
		loadClients.setBounds(1050, 520, 182, 35);
		getContentPane().add(loadClients);

		JButton searchButton = new JButton("SEARCH");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
				searchClientRezervation();
			}
		});
		searchButton.setBounds(121, 570, 101, 29);
		getContentPane().add(searchButton);

		idField = new JTextField();
		idField.setBounds(20, 520, 172, 31);
		getContentPane().add(idField);
		idField.setColumns(10);

		JButton deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteClientReservation();
			}
		});
		deleteButton.setBounds(10, 570, 101, 29);
		getContentPane().add(deleteButton);

		JLabel lblNewLabel = new JLabel("SEARCH BY NAME/DELETE BY ID");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel.setBounds(20, 239, 202, 30);
		getContentPane().add(lblNewLabel);

	}

	private void clearFields() {
		idField.setText("");
	}

	private void showClients() {
		String header[] = new String[] { "ID", "FIRST NAME", "LAST NAME", "PHONE", "HAIR REMOVAL", "EYEBROWS", "MANICURE",
				"PEDICURE" ,"DATE","HOUR","TOTAL PRICE"};
		dtm.setColumnIdentifiers(header);
		CustomerService service = new CustomerService();
		result = service.getCustomers();

		for (int i = 0; i < result.size(); i++) {
			dtm.addRow(new Object[] { result.get(i).getId(), result.get(i).getFirstName(), result.get(i).getLastName(),
					result.get(i).getPhoneNumber(), result.get(i).getHairRemoval(), result.get(i).getEyebrows(),
					result.get(i).getManicure(), result.get(i).getPedicure(),result.get(i).getReservationDate(),result.get(i).getReservationHour(),result.get(i).getTotalPrice()});
		}
		dtm.fireTableDataChanged();

	}

	private void deleteClientReservation() {
		try {
			customerService.delete(Integer.valueOf(idField.getText()));
			clearFields();
		} catch (HibernateException ee) {
			ee.printStackTrace();
		}
		clearFields();

	}

	private void searchClientRezervation() {
		String header[] = new String[] { "ID", "FIRST NAME", "LAST NAME", "PHONE", "HAIR REMOVAL", "EYEBROWS", "MANICURE",
				"PEDICURE" ,"DATE","HOUR","TOTAL PRICE"};
		dtm.setColumnIdentifiers(header);

		try {
			result = customerService.search(idField.getText());
			clearFields();
		} catch (HibernateException ee) {
			ee.printStackTrace();
		}

		for (int i = 0; i < result.size(); i++) {
			dtm.addRow(new Object[] { result.get(i).getId(), result.get(i).getFirstName(), result.get(i).getLastName(),
					result.get(i).getPhoneNumber(), result.get(i).getHairRemoval(), result.get(i).getEyebrows(),
					result.get(i).getManicure(), result.get(i).getPedicure(),result.get(i).getReservationDate(),result.get(i).getReservationHour(),result.get(i).getTotalPrice()});

		}
	}

}
