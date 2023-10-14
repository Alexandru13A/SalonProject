package ro.salon.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ro.salon.common.entity.Employee;
import ro.salon.hibernate.HibernateUtil;

public class EmployeeService {

   public List<Employee> getEmployees() {
    List<Employee> Employees = new ArrayList<>();
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
    Employees = query.getResultList();
    session.close();
    return Employees;
  }

  public void save(Employee Employee) {

    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.persist(Employee);
    session.getTransaction().commit();
    session.close();

  }

  public void delete(Integer id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Employee Employee = session.get(Employee.class, id);
    session.remove(Employee);
    session.flush();
    session.close();

  }

  public List<Employee> search(String name) {
    List<Employee> Employees = new ArrayList<>();
    Session session = HibernateUtil.getSessionFactory().openSession();

    Query<Employee> query = session.createQuery(
        "SELECT e FROM Employee e WHERE CONCAT(e.id, ' ', e.firstName, ' ', e.lastName) LIKE CONCAT('%', :name, '%')", Employee.class);
    query.setParameter("name", name);
    Employees = query.getResultList();

    session.close();

    return Employees;
  }
  
}