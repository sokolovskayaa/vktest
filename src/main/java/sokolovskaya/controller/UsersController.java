package sokolovskaya.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sokolovskaya.aud.SaveToAudit;
import sokolovskaya.dto.User;
import sokolovskaya.service.UsersService;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USERS', 'ADMIN')")
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    @SaveToAudit
    public User[] getUsers(HttpServletRequest request) {
        return usersService.getUsers();
    }

    @GetMapping("/{userId}")
    @SaveToAudit
    public User getUser(HttpServletRequest request, @PathVariable Long userId) {
        return usersService.getUser(userId);
    }


    @PostMapping
    @SaveToAudit
    public User postUser(HttpServletRequest request, @RequestBody User user) {
        return usersService.postUser(user);
    }

    @PutMapping("/{userId}")
    @SaveToAudit
    public User putUser(HttpServletRequest request, @RequestBody User user, @PathVariable Long userId) {
        return usersService.putUser(user, userId);
    }

    @DeleteMapping("/{userId}")
    @SaveToAudit
    public void deleteUser(HttpServletRequest request, @PathVariable Long userId) {
        usersService.deleteUser(userId);
    }
}
