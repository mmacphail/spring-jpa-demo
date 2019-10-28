package eu.macphail.springjpademo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long>, JpaSpecificationExecutor<Person> {
}
