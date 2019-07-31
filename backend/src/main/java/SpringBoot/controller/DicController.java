package SpringBoot.controller;

import SpringBoot.domain.dic.place_job;
import SpringBoot.repo_dic.PlaceJobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/*
* это способ через JpaRepository(зпрос за нас пишет Hibernate)
*
* другой способ  - через jdbcTemplate(запрос пишем сами) реализованный в PlaceJobController,
* */

@RestController
@RequestMapping("api")
public class DicController {

    @Autowired
    private final PlaceJobRepo placeJobRepo;

    public DicController(PlaceJobRepo placeJobRepo) {
        this.placeJobRepo = placeJobRepo;
    }

    @GetMapping("/getPlaceJob")
    public ArrayList<place_job> PlaceJob() {
        ArrayList<place_job> placeJobs = new ArrayList<>();
        placeJobs = (ArrayList<place_job>) placeJobRepo.findAll();
        return placeJobs ;
    }

}
