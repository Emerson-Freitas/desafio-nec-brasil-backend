package br.com.nec.brasil.desafio.repository;

import br.com.nec.brasil.desafio.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    UsuarioModel findByUsuarioAndSenha(String usuario, String senha);

    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.usuario = :usuario")
    Boolean existsByUsuario(@Param("usuario") String usuario);
}
