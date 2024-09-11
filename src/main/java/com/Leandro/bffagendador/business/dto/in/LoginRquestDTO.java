package com.Leandro.bffagendador.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRquestDTO {
    private String login;
    private String senha;
}
