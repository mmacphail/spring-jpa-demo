package eu.macphail.springjpademo;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Person {

    public Person(@NotNull String firstName, @NotNull String lastName, @NotNull ZonedDateTime dateOfBirth, @NotNull int numberOfChildren, @NotNull boolean married) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.numberOfChildren = numberOfChildren;
        this.married = married;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    @NotNull
    String firstName;

    @Column
    @NotNull
    String lastName;

    @Column
    @NotNull
    ZonedDateTime dateOfBirth;

    @Column
    @NotNull
    int numberOfChildren;

    @Column
    @NotNull
    boolean married;
}
