package br.com.nec.brasil.desafio.controller;

import br.com.nec.brasil.desafio.dto.UsuarioDTO;
import br.com.nec.brasil.desafio.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity listaUsuarios(@PageableDefault(size = 10) Pageable pageable){
        var usuarios = usuarioService.listaDeUsuarios(pageable);

        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO){
        var mensagemCadastro = usuarioService.cadastrarUsuario(usuarioDTO);

        return ResponseEntity.ok(mensagemCadastro);
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity realizarLogin(@RequestBody @Valid UsuarioDTO usuarioDTO){
        var mensagemLogin = usuarioService.realizaLogin(usuarioDTO);

        return ResponseEntity.ok(mensagemLogin);
    }


}
