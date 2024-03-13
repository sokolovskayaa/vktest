package sokolovskaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sokolovskaya.repository.dto.Audit;

import java.util.UUID;

public interface AuditRepository extends JpaRepository<Audit, UUID> {
}

