package hello.web;

import java.util.List;

import hello.domain.Customer;
import hello.gfrepositories.GemfireCustomerRepository;
import hello.jparepositories.JPACustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
public class CustomerResource {

	@Autowired
	JPACustomerRepository jpaRepository;

	@Autowired
	GemfireCustomerRepository gfRepository;

	@RequestMapping("/getGfCustomers")
	public List<Customer> findAllGfCustomers() {

		Iterable<Customer> allCustomers = gfRepository.findAll();
		List<Customer> allCustomersList = Lists.newArrayList(allCustomers);
		return allCustomersList;
	}

	@RequestMapping("/getJpaCustomers")
	public List<Customer> findAllJpaCustomers() {

		Iterable<Customer> allCustomers = jpaRepository.findAll();
		List<Customer> allCustomersList = Lists.newArrayList(allCustomers);
		return allCustomersList;
	}
	
	@RequestMapping("/createCustomers")
	public String createCustomers() {

		jpaRepository.save(new Customer(1, "Karim", "Bauer"));
		jpaRepository.save(new Customer(2, "Chloe", "OBrian"));
		jpaRepository.save(new Customer(3, "Kim", "Bauer"));
		jpaRepository.save(new Customer(4, "David", "Palmer"));
		jpaRepository.save(new Customer(5, "Michelle", "Dessler"));

		for (Customer customer : jpaRepository.findAll()) {
			System.out.println(customer);
			gfRepository.save(customer);
		}
		return "Created 5 customers.";
	}
}
