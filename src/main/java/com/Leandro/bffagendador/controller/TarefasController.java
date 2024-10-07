package com.Leandro.bffagendador.controller;



import com.Leandro.bffagendador.business.TarefasService;
import com.Leandro.bffagendador.business.dto.in.TarefasDTORequest;
import com.Leandro.bffagendador.business.dto.out.TarefasDTOResponse;
import com.Leandro.bffagendador.business.enums.StatusNotificacaoEnum;
import com.Leandro.bffagendador.infrastructure.client.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro  tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de  Usuários" , description = "Criar um nova tarefa")
    @ApiResponse(responseCode = "200" , description = "Tarefa  salva com sucesso")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(name = "authorization", required=false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token , dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por período " , description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200" , description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListadeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "authorization", required=false) String token) {

            return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));

    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email de usúario " ,
            description = "Busca tarefas cadastradas por usuario")
    @ApiResponse(responseCode = "200" , description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "authorization", required=false) String token){
        //List<TarefasDTO> tarefas = tarefasService.buscaTarefasPorEmail(token); //Apenas demostrando forma extensa
        //return ResponseEntity.ok(tarefas);
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por id " , description = "Deleta tarefas cadastradas por id")
    @ApiResponse(responseCode = "200" , description = "Tarefas deletas com sucesso")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorid(@RequestParam("id") String id,
                                                  @RequestHeader(name = "authorization", required=false) String token){
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de  tarefas " , description = "Altera status das tarefas cadastradas ")
    @ApiResponse(responseCode = "200" , description = "Status da tarefas  alteradas com sucesso")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "authorization", required=false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas  " , description = "Altera dados das  tarefas cadastradas")
    @ApiResponse(responseCode = "200" , description = "Tarefas alteradas com sucesso")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas (@RequestBody TarefasDTORequest dto,
                                                             @RequestParam("id") String id,
                                                             @RequestHeader(name = "authorization", required=false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }


}
















