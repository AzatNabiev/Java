package ru.itis.javalab.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class PostDto {
    private Long postId;
    private Long userId;
    private String text;
}
