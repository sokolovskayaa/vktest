package sokolovskaya.repository.dto;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table
@NoArgsConstructor
public class Audit {
    @Id
    @GeneratedValue
    private UUID id;
    private OffsetDateTime datetime;
    private String username;
    private String uri;
    @Column(name = "request_type")
    private String requestType;


    public Audit(HttpServletRequest request) {
        this.uri = request.getRequestURI();
        this.requestType = request.getMethod();
        this.username = request.getRemoteUser();
        this.datetime = OffsetDateTime.now();
    }
}
