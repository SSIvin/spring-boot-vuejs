package SpringBoot.controller;

import SpringBoot.repo.PersonRepo;
import SpringBoot.domain.Person;
import SpringBoot.domain.Views;
import SpringBoot.util.ExcelGenerator;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private final PersonRepo personRepo;

    public MessageController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping("/persons")
    public List<Person> ListPerson() {

//        Person p = new Person("Иванов","Иван","Иванович");
//
//        List<Person> items = new ArrayList<>();
//        items.add(p);

//          personRepo.findAll().forEach(msg ->log.debug("message:{}",msg.toString()));
        return personRepo.findAll();
//        return items;
    }

    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    @RequestMapping(value = "{_:^(?!index\\.html|api).$}")
    public String redirectApi() {
        return "forward:/";
    }

   // @RequestMapping(value = "/find", method = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping(path = "/find")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonView(Views.IdName.class)
    public List<Person>  Find(@RequestBody Person person)
    {
        System.out.println(person);
//        personRepo.FindPerson(person).forEach(msg ->log.debug("message:{}",msg.toString()));
        return personRepo.FindPerson(person);
    }

    @PostMapping(path = "/addFile")
    void addFile(
            @RequestParam("file") MultipartFile file)
            throws IOException {

//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
//                stream.write(bytes);
//                stream.close();
//            } catch (Exception e) {
//            }
//        }
//
        file.transferTo(new File("C:/" + file.getOriginalFilename()));
    }

    @PostMapping("/findOne")
    public Person FindPerson(@RequestBody Long id)
    {
        System.out.println("FindPersonOne:" + id);
        return  personRepo.FindPerson(id);

    }

    @DeleteMapping("/delete/{id}")
    public void delete(
            @PathVariable("id") Long id) {
        personRepo.DeletePerson(id);
    }

    @PutMapping("/update/{id}")
    public void update(
            @PathVariable("id") Long personFromDb,
            @RequestBody Person person
    ) {
        //BeanUtils.copyProperties(person, personFromDb, "id");

        personRepo.SavePerson(person);

        System.out.println(personFromDb);
        System.out.println(person);
        //return updatedMessage;
    }

    @PostMapping("/add")
    public void create(
            @RequestBody Person person
    ) {
        personRepo.CreatePerson(person);

//        System.out.println(personFromDb);
//        System.out.println(person);
        //return updatedMessage;
    }


//    @GetMapping("persons/{id}")
//    public Person FindPerson(@PathVariable Long id) {
//        return  personRepo.FindPerson(id);
//    }


    @GetMapping(value = "/download/customers.xlsx")
    public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
        List<Person> persons = personRepo.findOne();

        MediaType mediaType = MediaType.parseMediaType("application/vnd.ms-excel");

        ByteArrayInputStream in = ExcelGenerator.customersToExcel(persons);
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=persons.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(mediaType)
                .body(new InputStreamResource(in));
    }



}
