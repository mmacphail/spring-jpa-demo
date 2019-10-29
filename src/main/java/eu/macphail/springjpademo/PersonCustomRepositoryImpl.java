package eu.macphail.springjpademo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PersonCustomRepositoryImpl implements PersonCustomRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<String> getFirstTenLastName() {
        return this.em.createNativeQuery(
                "select distinct(first_name) from person order by first_name asc limit 10;")
                .getResultList();
    }
}
