package br.com.nec.brasil.desafio.service;

import br.com.nec.brasil.desafio.dto.UsuarioDTO;
import br.com.nec.brasil.desafio.model.UsuarioModel;
import br.com.nec.brasil.desafio.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<UsuarioModel> listaDeUsuarios(Pageable pageable){

        return usuarioRepository.findAll(pageable);
    }

    public Boolean existeUsuario(String usuario){
        return usuarioRepository.existsByUsuario(usuario);
    }

    public String cadastrarUsuario(UsuarioDTO usuarioDTO){
        var existeUsuario = existeUsuario(usuarioDTO.getUsuario());

        if(existeUsuario){
            return "O usuario " + usuarioDTO.getUsuario() + " já foi cadastrado!";
        }

        var usuario = modelMapper.map(usuarioDTO, UsuarioModel.class);
        usuarioRepository.save(usuario);

        return "Usuario cadastrado com sucesso";
    }

    public String realizaLogin(UsuarioDTO usuarioDTO){
        var usuarioModel = usuarioRepository.findByUsuarioAndSenha(usuarioDTO.getUsuario(), usuarioDTO.getSenha());

        var mensagemLogin = "";

        if(usuarioModel == null || !usuarioModel.getUsuario().equals(usuarioDTO.getUsuario())){
            mensagemLogin = "Falha ao realizar login, usuário ou senha incorreto!";
            return mensagemLogin;
        }

        if(!usuarioModel.getSenha().equals(usuarioDTO.getSenha())){
            mensagemLogin =  "Falha ao realizar login senha incorreta!";
            return mensagemLogin;
        }

        if(usuarioModel.getUsuario().equals(usuarioDTO.getUsuario()) && usuarioModel.getSenha().equals(usuarioModel.getSenha())){
            mensagemLogin =  "Login realizado com sucesso!";
            return mensagemLogin;
        }

        return null;
    }
}
