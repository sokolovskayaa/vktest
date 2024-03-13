package sokolovskaya.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sokolovskaya.repository.AuditRepository;
import sokolovskaya.repository.dto.Audit;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;

    public void save(HttpServletRequest request) {
        auditRepository.save(new Audit(request));
    }
}
