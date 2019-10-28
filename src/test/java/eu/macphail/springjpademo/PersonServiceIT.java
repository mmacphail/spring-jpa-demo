package eu.macphail.springjpademo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class PersonServiceIT {

    @Autowired
    PersonService personService;

    @Test
    void findAPersonWillAllCriteriaShouldReturnAResult() {
        ZonedDateTime dob = LocalDateTime.of(1990, Month.OCTOBER, 27, 10, 30, 0).atZone(ZoneId.of(
                "Europe/Paris"
                ));
        List<Person> persons = personService.findPerson("John","Doe", dob, 0, true);
        assertThat(persons).hasSize(1);

        log.info("persons are " + persons);
    }

    @Test
    void findAPersonWillSomeCriteriaShouldReturnAResult() {
        List<Person> persons = personService.findPerson(null,"Doe", null, null, true);
        assertThat(persons).hasSize(1);

        log.info("persons are " + persons);
    }

    @Test
    void findPersonBornDuringARangeReturnResults() {
        ZonedDateTime start = LocalDateTime.of(1985, Month.OCTOBER, 27, 10, 30, 0).atZone(ZoneId.of(
                "Europe/Paris"
        ));
        ZonedDateTime finish = LocalDateTime.of(1995, Month.OCTOBER, 27, 10, 30, 0).atZone(ZoneId.of(
                "Europe/Paris"
        ));
        List<Person> persons = personService.findPersonsBornBetween(start, finish);
        assertThat(persons.size()).isGreaterThanOrEqualTo(1);

        persons.forEach(person ->
            log.info(person.firstName + " " + person.lastName + " born the " + person.dateOfBirth)
        );
    }
}