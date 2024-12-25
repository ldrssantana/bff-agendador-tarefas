package com.Leandro.bffagendador.business;


import com.Leandro.bffagendador.business.dto.out.TarefasDTOResponse;
import com.Leandro.bffagendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    //Converter de DTO para Entity
    public void enviaEmail(TarefasDTOResponse dto) {
        emailClient.enviaEmail(dto);
    }


}
