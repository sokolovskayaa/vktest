package sokolovskaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sokolovskaya.repository.dto.MyUser;

import java.util.Optional;
import java.util.UUID;

public interface MyUserRepository extends JpaRepository<MyUser, UUID> {
    Optional<MyUser> findByUsername(String username);
}

