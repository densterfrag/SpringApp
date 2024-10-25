package com.mark_ainad.task_7.DTO;

import com.mark_ainad.task_7.entity.Role;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Component
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 30, message = "name size should be between 2 and 30")
    private String username;

    @NotEmpty(message = "lastname should be bot empty")
    private String lastName;

    private int dickSize;

    @NotEmpty(message = "Password should be not empty")
    private String password;

    private List<String> roles;
}
