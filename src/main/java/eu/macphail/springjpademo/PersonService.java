package eu.macphail.springjpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> findPerson(String firstName,
                                   String lastName,
                                   ZonedDateTime dob,
                                   Integer numberOfChildren,
                                   Boolean married) {

        return personRepository.findAll(
            personHasFirstName(firstName)
                    .and(personHasLastName(lastName))
                    .and(personHasDob(dob))
                    .and(personHasNumberOfChildren(numberOfChildren))
                    .and(personIsMarried(married))
        );
    }

    public List<Person> findPersonsBornBetween(ZonedDateTime start, ZonedDateTime end) {
        return personRepository.findAll(
                personBornAfter(start).and(personBornBefore(end))
        );
    }

    public List<String> getFirstTenLastNames() {
        return personRepository.getFirstTenLastName();
    }

    private Specification<Person> personBornBefore(ZonedDateTime date) {
        return (person, q, cb) -> cb.lessThanOrEqualTo(person.get("dateOfBirth"), date);
    }

    private Specification<Person> personBornAfter(ZonedDateTime date) {
        return (person, q, cb) -> cb.greaterThanOrEqualTo(person.get("dateOfBirth"), date);
    }

    private Specification<Person> personHasFirstName(String firstName) {
        return personHas("firstName", firstName);
    }

    private Specification<Person> personHasLastName(String lastName) {
        return personHas("lastName", lastName);
    }

    private Specification<Person> personHasDob(ZonedDateTime zdt) {
        return personHas("dateOfBirth", zdt);
    }

    private Specification<Person> personHasNumberOfChildren(Integer numberOfChildren) {
        if(numberOfChildren != null) {
            return (person, q, cb) -> cb.equal(person.get("numberOfChildren"), numberOfChildren);
        } else {
            return trueSpec;
        }
    }

    private Specification<Person> personIsMarried(Boolean married) {
        if(married != null) {
            return (person, q, cb) -> cb.equal(person.get("married"), married);
        } else {
            return trueSpec;
        }
    }

    private Specification<Person> personHas(String field, String value) {
        if(StringUtils.hasText(value)) {
            return (person, q, cb) -> cb.equal(person.get(field), value);
        } else {
            return trueSpec;
        }
    }

    private Specification<Person> personHas(String field, Object value) {
        if(value != null) {
            return (person, q, cb) -> cb.equal(person.get(field), value);
        } else {
            return trueSpec;
        }
    }

    private static final Specification<Person> trueSpec = (person, q, cb) -> cb.conjunction();
}
