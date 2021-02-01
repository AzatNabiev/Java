package ru.itis.javalab.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class FileInfo {
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
}
