package eu.macphail.springjpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PersonLoader {

    @Autowired
    PersonRepository personRepository;

    private final int numberOfPersons = 1_000;

    @PostConstruct
    void init() {
        List<Person> persons = IntStream.range(0, numberOfPersons)
                .mapToObj(i -> getRandomPerson())
                .collect(Collectors.toList());

        List<Person> allPersons = new ArrayList<>(persons);
        allPersons.add(new Person("John", "Doe", LocalDateTime.of(1990, Month.OCTOBER, 27, 10, 30, 0).atZone(ZoneId.of(
                "Europe/Paris"
        )), 0, true));

        personRepository.saveAll(allPersons);
    }

    private Person getRandomPerson() {
        return new Person(randomFirstName(), randomLastName(), randomDob(), randomNumberOfChildren(), randomMarried());
    }

    private Random rng = new Random();

    private static final List<String> firstName = Arrays.asList("GroGal", "NukHam", "LuLen", "GienAd",
            "OlodZelon", "ViezaOyom", "YuhuOmonm", "GruyakHohitt", "VeetGad", "ZiZot");

    private String randomFirstName() {
        return firstName.get(rng.nextInt(firstName.size()));
    }

    private static final List<String> lastName = Arrays.asList("ZhitZul", "VungTiilm", "TeVatt", "LieleTahalm",
            "IenuOyatt", "EziVeneln", "TheAt", "VraSiid", "GentNa", "TalYo");

    private String randomLastName() {
        return lastName.get(rng.nextInt(lastName.size()));
    }

    private ZonedDateTime randomDob() {
        return ZonedDateTime.of(
                LocalDate.of(
                        1970 + rng.nextInt(55),
                        rng.nextInt(12) + 1,
                        rng.nextInt(27) + 1
                ),
                LocalTime.NOON,
                ZoneId.of("Europe/Paris")
        );
    }

    private int randomNumberOfChildren() {
        return rng.nextInt(4);
    }

    private boolean randomMarried() {
        return rng.nextInt(1) != 0;
    }
}
