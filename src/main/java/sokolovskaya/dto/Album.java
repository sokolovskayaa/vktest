package sokolovskaya.dto;

import lombok.Data;

@Data
public class Album {
    private Long id;
    private Long userId;
    private String title;
}
