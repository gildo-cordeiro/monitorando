package br.com.imd.pdse.monitorando.domain.dto;

import br.com.imd.pdse.monitorando.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String name;
    private String login;
    private String pass;
    private UserType userType;
}
