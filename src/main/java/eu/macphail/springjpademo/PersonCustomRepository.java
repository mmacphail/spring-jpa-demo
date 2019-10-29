package eu.macphail.springjpademo;

import java.util.List;

public interface PersonCustomRepository {

    List<String> getFirstTenLastName();
}
