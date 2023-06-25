package br.com.imd.pdse.monitorando.domain.dto;

import br.com.imd.pdse.monitorando.domain.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private static final String DEFAULT_MESSAGE = "O CAMPO NÃO PODE ESTAR EM BRANCO";

    @NotBlank(message = DEFAULT_MESSAGE)
    private String name;
    @NotBlank(message = DEFAULT_MESSAGE)
    @Email(message = "O login precisa ser um email valido")
    private String login;
//    @NotBlank(message = DEFAULT_MESSAGE)
//    @Min(value = 8, message = "A senha precisa ter tamnho minimo de 8 caracteres")
//    @Max(value = 16, message = "A senha precisa ter tamnho maximo de 16 caracteres")
    private String pass;
    private UserType userType;
}
