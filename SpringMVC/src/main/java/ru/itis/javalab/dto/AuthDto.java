package ru.itis.javalab.dto;

import lombok.*;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class AuthDto {
    private String password;
    private String email;
    public static AuthDto from(User user){
        return AuthDto.builder()
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
    public static List<AuthDto> from(List<User> users){
        return users.stream().map(AuthDto::from).collect(Collectors.toList());
    }
}
