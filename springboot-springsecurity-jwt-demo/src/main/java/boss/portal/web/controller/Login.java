package boss.portal.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import boss.portal.entity.User;

@RestController
public class Login {

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return "save";
    }
}
