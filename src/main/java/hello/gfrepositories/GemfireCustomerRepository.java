package hello.gfrepositories;

import java.util.List;

import hello.domain.Customer;

import org.springframework.data.repository.CrudRepository;

public interface GemfireCustomerRepository  extends CrudRepository<Customer, Long>{

	List<Customer> findByLastName(String lastName);
	
}