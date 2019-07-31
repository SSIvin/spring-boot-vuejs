package SpringBoot.service;

import SpringBoot.domain.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapperB implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person p = new Person(0,"","","");
        p.setId(resultSet.getLong("id"));
        p.setF(resultSet.getString("f"));
        p.setI(resultSet.getString("i"));
        p.setO(resultSet.getString("o"));
        p.setPlaceJob(resultSet.getLong("placeJob"));
        if (resultSet.findColumn("birthday") == 5) {
            p.setBirthday(resultSet.getDate("birthday"));
        }
        return p;
    }
}
