package com.Leandro.bffagendador.controller;


import com.Leandro.bffagendador.business.UsuarioService;
import com.Leandro.bffagendador.business.dto.in.EnderecoDTORequest;
import com.Leandro.bffagendador.business.dto.in.LoginRquestDTO;
import com.Leandro.bffagendador.business.dto.in.TelefoneDTORequest;
import com.Leandro.bffagendador.business.dto.in.UsuarioDTORquest;
import com.Leandro.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.Leandro.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.Leandro.bffagendador.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro  e login e usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar Usuários" , description = "Criar um novo usuário")
    @ApiResponse(responseCode = "200" , description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400" , description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORquest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));

    }

    //DTO pode ser de Request quando recebe ou Response, quando ele responde esses dados, ele faz esse filtro.
    @PostMapping("/login")
    @Operation(summary = "Login  Usuários" , description = "Login do usuário")
    @ApiResponse(responseCode = "200" , description = "Usuario logado")
    @ApiResponse(responseCode = "401" , description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public String login(@RequestBody LoginRquestDTO usuarioDTO){

        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuários por email" ,
            description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200" , description = "Usuario encontrado")
    @ApiResponse(responseCode = "404" , description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader("Authorization") String  token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuário por id" , description = "Deleta usuário")
    @ApiResponse(responseCode = "200" , description = "Usuario deletado")
    @ApiResponse(responseCode = "404" , description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable("email") String email,
                                                      @RequestHeader("Authorization") String  token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de  Usuários" , description = "Atualiza  dados usuários")
    @ApiResponse(responseCode = "200" , description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404" , description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORquest dto,
                                                                   @RequestHeader(name = "authorization", required=false) String token){

        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizado Endereço de  Usuários" , description = "Atualiza endereço de usuários")
    @ApiResponse(responseCode = "200" , description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404" , description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader("Authorization") String  token){

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizado telefone de Usuários" , description = "Atualiza  telefone de  usuário")
    @ApiResponse(responseCode = "200" , description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404" , description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader("Authorization") String  token){

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salvar Endereço de  Usuários" , description = "Salva endereço de usuários")
    @ApiResponse(responseCode = "200" , description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404" , description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader("Authorization") String  token){

        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }


    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone de  Usuários" , description = "Salva telefone  de usuários")
    @ApiResponse(responseCode = "200" , description = "Telefone  salvo com sucesso")
    @ApiResponse(responseCode = "404" , description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader("Authorization") String  token){

        return ResponseEntity.ok(usuarioService.cadastrarTelefone(token, dto));
    }

}
