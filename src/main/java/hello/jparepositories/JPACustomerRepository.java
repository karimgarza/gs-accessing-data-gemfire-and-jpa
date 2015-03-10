package hello.jparepositories;

import hello.domain.Customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface JPACustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
