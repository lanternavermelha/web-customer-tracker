package springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		//create query ... sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);

		//get the list of customers from the query
		List<Customer> customers = theQuery.getResultList();

		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		//save the customer
		currentSession.save(theCustomer);

	}

	@Override
	public Customer getCustomers(int theId) {

		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		//now retrieve/read form database using the primary id (theId)
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

}
