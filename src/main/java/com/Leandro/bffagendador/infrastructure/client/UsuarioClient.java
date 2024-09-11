package com.Leandro.bffagendador.infrastructure.client;


import com.Leandro.bffagendador.business.dto.in.EnderecoDTORequest;
import com.Leandro.bffagendador.business.dto.in.LoginRquestDTO;
import com.Leandro.bffagendador.business.dto.in.TelefoneDTORequest;
import com.Leandro.bffagendador.business.dto.in.UsuarioDTORequest;

import com.Leandro.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.Leandro.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.Leandro.bffagendador.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {


    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("authorization") String token);



    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTOResponse usuarioDTO);



    //DTO pode ser de Request quando recebe ou Response, quando ele responde esses dados, ele faz esse filtro.
    @PostMapping("/login")
     String login(@RequestBody LoginRquestDTO usuarioDTO);



    @DeleteMapping("/{email}")
     void deletaUsuarioPorEmail(@PathVariable("email") String email,
                                                      @RequestHeader("Authorization") String  token);


    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader("Authorization") String token);


    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String  token);


    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String  token);


    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String  token);



    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String  token);


}
