package hello;

import hello.domain.Customer;
import hello.gfrepositories.GemfireCustomerRepository;
import hello.jparepositories.JPACustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.support.GemfireCacheManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.GemFireCache;

@SpringBootApplication
@EnableGemfireRepositories(basePackages={"hello.gfrepositories"})
@EnableJpaRepositories(basePackages={"hello.jparepositories"})
public class Application implements CommandLineRunner {


	@Autowired
	JPACustomerRepository repository;

	@Autowired
	GemfireCustomerRepository gfRepository;
    
    @Autowired
    GemfireCacheManager cacheManager;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    CacheFactoryBean cacheFactoryBean() {
        return new CacheFactoryBean();
    }

    @Bean
    LocalRegionFactoryBean<Long, Customer> localRegionFactory(final GemFireCache cache) {
        return new LocalRegionFactoryBean<Long, Customer>() {
            {
                setCache(cache);
                setName("Customer");
                setClose(false);
            }
        };
    }
    
    @Bean
    GemfireCacheManager cacheManager(final Cache gemfireCache) {
        return new GemfireCacheManager() {{
            setCache(gemfireCache);
        }};
    }

	@Override
	public void run(String... args) throws Exception {
		
		repository.save(new Customer(1, "Karim", "Garza"));
		repository.save(new Customer(2, "Chloe", "OBrian"));
		repository.save(new Customer(3, "Kim", "Bauer"));
		repository.save(new Customer(4, "David", "Palmer"));
		repository.save(new Customer(5, "Michelle", "Dessler"));

		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
			gfRepository.save(customer);
		}				
	}
}
