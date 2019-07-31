package SpringBoot.repo;

import SpringBoot.domain.Person;
import SpringBoot.service.PersonMapper;
import SpringBoot.service.PersonMapperB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepo {



    final String ff = "SELECT id, f\n" +
            ",i\n" +
            ",o\n" +
            "FROM   person\n" +
            "WHERE f LIKE :f\n" +
            "       and   i LIKE :i\n" +
            "       AND   o LIKE :o";

    final String UPDATE_PERSON = "UPDATE [person] SET [f] = :f WHERE id = :id";
    final String DELETE_PERSON = "DELETE from person where id = :id";
    final String CREATE_PERSON = "INSERT INTO person (f,i,o) VALUES (:f,:i,:o)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PersonRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public List<Person> findAll() {
        List<Person> list = jdbcTemplate.query("SELECT top(10) id,f,i,o FROM person", (rs, i) ->
                new Person(rs.getLong("id")
                        , rs.getString("f")
                        , rs.getString("i")
                        , rs.getString("o"))
        );
        return list;

    }

    /**
     * FindOne
     *
     * @return
     */
    public List<Person> findOne() {
        List<Person> list = jdbcTemplate.query("SELECT top(5) * FROM person", (rs, i) ->
                new Person(rs.getLong("id")
                        , rs.getString("f")
                        , rs.getString("i")
                        , rs.getString("o")
                        , rs.getDate("birthday")
                        , rs.getLong("placeJob")
                )
        );
        return list;

    }

    /**
     * @param person
     * @return
     */
    public List<Person> FindPerson(Person person) {
        return namedParameterJdbcTemplate.query(ff
                , new MapSqlParameterSource()
                        .addValue("f", person.getF() + "%")
                        .addValue("i", person.getI() + "%")
                        .addValue("o", person.getO() + "%")
                , new PersonMapper()
        );
    }


/**
 * через ламда выражение
 */
//    public Person FindPerson(Long id) {
//        Person person = jdbcTemplate.queryForObject("SELECT id,f,i,o FROM person where id=?", (rs, i) -> new Person(rs.getLong("id")
//                , rs.getString("f")
//                , rs.getString("i")
//                , rs.getString("o")), id
//        );
//
//        System.out.println(person);
//        return person;
//
//
//    }
/**
 *    через собственный Mapper
 */

//    public Person FindPerson(Long id) {
//        Person person = jdbcTemplate.queryForObject("SELECT id,f,i,o FROM person where id=?"
//                ,new PersonMapper()
//                ,id
//        );
//        System.out.println(person);
//        return person;
//    }

    /**
     *через BeanPropertySqlParameterSource - сам определяет параметры. Почему то только Count можно посчитать
     */

//public Person FindPerson(Long id) {
//    Person person = new Person();
//    person.setId(id);
//    SqlParameterSource params = new BeanPropertySqlParameterSource(person);
//
//    namedParameterJdbcTemplate.queryForObject("SELECT count(*) FROM person where id=:id",params,Long.class);
//    System.out.println(person);
//    return person;
//}

    /**
     * через MapSqlParameterSource
     */

    public Person FindPerson(Long id) {

        List<Person> person = namedParameterJdbcTemplate.query("SELECT * FROM person where id=:id"
                , new MapSqlParameterSource("id", id)
                , new PersonMapperB()
        );

        if (person.size() == 1) {
            return person.get(0);
        }
        return null;
    }


    public void SavePerson(Person person) {

        int status = namedParameterJdbcTemplate.update(UPDATE_PERSON
                , new MapSqlParameterSource()
                        .addValue("id", person.getId())
                        .addValue("f", person.getF())
        );
        if(status != 0){
            System.out.println("Person data updated for ID " + person.getId());
        }else{
            System.out.println("No Person found with ID " + person.getId());
        }
    }

    public void CreatePerson(Person person) {

        int status = namedParameterJdbcTemplate.update(CREATE_PERSON
                , new MapSqlParameterSource()
                        .addValue("f", person.getF())
                        .addValue("i", person.getI())
                        .addValue("o", person.getO())
        );
    }
    public void DeletePerson(Long id) {

        int status = namedParameterJdbcTemplate.update(DELETE_PERSON
                , new MapSqlParameterSource()
                        .addValue("id", id)
        );
        if(status != 0){
            System.out.println("Person data delete for ID " + id.toString());
        }else{
            System.out.println("No Person found with ID " + id.toString());
        }
    }

}
