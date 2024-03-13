package sokolovskaya.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sokolovskaya.repository.dto.MyUser;
import sokolovskaya.service.MyUserService;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService myUserService;

    @PostMapping("/new_user")
    public String addNewUser(@RequestBody MyUser user) {
        return myUserService.addUser(user);
    }
}
