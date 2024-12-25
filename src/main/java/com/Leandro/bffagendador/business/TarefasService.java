package com.Leandro.bffagendador.business;


import com.Leandro.bffagendador.business.dto.in.TarefasDTORequest;
import com.Leandro.bffagendador.business.dto.out.TarefasDTOResponse;
import com.Leandro.bffagendador.business.enums.StatusNotificacaoEnum;
import com.Leandro.bffagendador.infrastructure.client.TarefasClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    //Converter de DTO para Entity
    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {
        return tarefasClient.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal,
                                                                    String token) {
        return tarefasClient.buscaListadeTarefasPorPeriodo(dataInicial, dataFinal, token);

    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefasClient.deletaTarefaPorid(id, token);


    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alteraStatusNotificacao(status, id, token);

    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id , String token) {
      return   tarefasClient.updateTarefas(dto, id, token);



    }
}
