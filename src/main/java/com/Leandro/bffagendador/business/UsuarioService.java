package com.Leandro.bffagendador.business;


import com.Leandro.bffagendador.business.dto.in.EnderecoDTORequest;
import com.Leandro.bffagendador.business.dto.in.LoginRquestDTO;
import com.Leandro.bffagendador.business.dto.in.TelefoneDTORequest;
import com.Leandro.bffagendador.business.dto.in.UsuarioDTORquest;
import com.Leandro.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.Leandro.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.Leandro.bffagendador.business.dto.out.UsuarioDTOResponse;
import com.Leandro.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

     private final UsuarioClient client;


     public UsuarioDTOResponse salvaUsuario(UsuarioDTORquest usuarioDTO) {

         return client.salvaUsuario(usuarioDTO);
     }

     public String loginUsuario(LoginRquestDTO dto) {
          return client.login(dto);
     }


     public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String Token) {
          return client.buscarUsuarioPorEmail(email, Token);

     }

     public void deletaUsuarioPorEmail(String email, String token) {

           client.deletaUsuarioPorEmail(email, token);
     }

     public UsuarioDTOResponse atualizaDadosUsuario(String token , UsuarioDTORquest dto){
         return client.atualizaDadosUsuario(dto,token);


     }

     public EnderecoDTOResponse atualizaEndereco(Long idEndereco , EnderecoDTORequest enderecoDTO, String token){
         return client.atualizaEndereco(enderecoDTO, idEndereco, token);

     }

     public TelefoneDTOResponse atualizaTelefone(Long idTelefone , TelefoneDTORequest dto, String token){
         return client.atualizaTelefone(dto, idTelefone, token);

     }

     public EnderecoDTOResponse cadastraEndereco(String token , EnderecoDTORequest dto){
         return client.cadastraEndereco(dto,token);


     }
     public TelefoneDTOResponse cadastrarTelefone(String token , TelefoneDTORequest dto){
          return client.cadastraTelefone(dto,token);


     }


}
