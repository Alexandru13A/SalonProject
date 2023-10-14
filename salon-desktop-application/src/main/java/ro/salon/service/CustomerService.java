package ro.salon.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ro.salon.common.entity.Customer;
import ro.salon.hibernate.HibernateUtil;

public class CustomerService {

  public List<Customer> getCustomers() {
    List<Customer> customers = actualizedCustomerList();
    return customers;
  }

  public List<Customer> actualizedCustomerList() {
    List<Customer> currentCustomers = new ArrayList<>();
    List<Customer> actualizedCustomers = new ArrayList<>();

    List<Integer> oldCustomerIds = new ArrayList<>();

    LocalDate now = LocalDate.now();

    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();

      Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
      currentCustomers = query.getResultList();

      for (Customer customer : currentCustomers) {
        if (customer.getReservationDate().isBefore(now)) {
          oldCustomerIds.add(customer.getId());
        } else {
          actualizedCustomers.add(customer);
        }
      }

      if (!oldCustomerIds.isEmpty()) {
        Query<Void> deleteQuery = session.createQuery("DELETE FROM Customer c WHERE c.id IN (:ids)", null);
        deleteQuery.setParameter("ids", oldCustomerIds);
        deleteQuery.executeUpdate();
      }
      transaction.commit();

    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw e;
    } finally {
      session.close();
    }

    return actualizedCustomers;
  }

  public void save(Customer customer) {

    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.persist(customer);
    session.getTransaction().commit();
    session.close();

  }

  public void delete(Integer id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Customer customer = session.get(Customer.class, id);
    session.remove(customer);
    session.flush();
    session.close();

  }

  public List<Customer> search(String name) {
    List<Customer> customers = new ArrayList<>();
    Session session = HibernateUtil.getSessionFactory().openSession();

    Query<Customer> query = session.createQuery(
        "SELECT c FROM Customer c WHERE CONCAT(c.id, ' ', c.firstName, ' ', c.lastName) LIKE CONCAT('%', :name, '%')",
        Customer.class);
    query.setParameter("name", name);
    customers = query.getResultList();

    session.close();

    return customers;
  }

  public List<Customer> getUnavailableHours(LocalDate date) {
    List<Customer> customers = new ArrayList<>();
    Session session = HibernateUtil.getSessionFactory().openSession();

    Query<Customer> query = session
        .createQuery("SELECT c.reservationHour FROM Customer c WHERE c.reservationDate = :date", Customer.class);
    query.setParameter("date", date);

    customers = query.getResultList();

    session.close();
    return customers;
  }

}
