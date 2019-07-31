package SpringBoot.repo;


import SpringBoot.domain.dic.place_job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class dicRepo {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<place_job> findAll() {
        List<place_job> list = jdbcTemplate.query("SELECT * FROM place_job", (rs, i) ->
                new place_job(rs.getLong("id")
                        , rs.getString("name")
                )
        );
        return list;

    }


}
