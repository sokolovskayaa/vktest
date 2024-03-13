package sokolovskaya.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sokolovskaya.repository.MyUserRepository;
import sokolovskaya.repository.dto.MyUser;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final PasswordEncoder passwordEncoder;
    private final MyUserRepository repository;

    public String addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "User added successfully!";
    }
}