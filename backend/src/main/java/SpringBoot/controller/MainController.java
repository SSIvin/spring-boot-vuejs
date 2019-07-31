package SpringBoot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

//    private final MessageRepo messageRepo;

//    @Autowired
//    public MainController(MessageRepo messageRepo) {
//        this.messageRepo = messageRepo;
//    }

    @Value("${spring.profiles.active:prod}")
    private String profile;

    @GetMapping
//    public String main() {
    public String main(Model model, Principal user) {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", user);
//            data.put("messages", messageRepo.findAll());
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
