package com.algaworks.algafoodreysson.domain.service;

import com.algaworks.algafoodreysson.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodreysson.domain.exceptions.NegocioException;
import com.algaworks.algafoodreysson.domain.exceptions.UsuarioNaoEncontradoException;
import com.algaworks.algafoodreysson.domain.model.Usuario;
import com.algaworks.algafoodreysson.domain.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final String MSG_USUARIO_NAO_ENCONTRADO = "Não existe cadastro"
            + " de usuário com códido %d";

    private static final String MSG_USUARIO_EM_USO = "O usuario de código %d"
            + " não pode ser removido pois está em uso";

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(usuario.getEmail());
        
        if(usuarioBuscado.isPresent() && !usuarioBuscado.get().equals(usuario)){
            throw new NegocioException(String.format("Já existe um usuario cadastrado com o email %s", usuario.getEmail()));
        }
        
        return usuarioRepository.save(usuario);
    }

    public void deletar(Integer id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(String.format(MSG_USUARIO_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, id));
        }
    }

    
    public void alterarSenha(Integer idUsuario, String senhaAntiga, String novaSenha) {
        Usuario usuario = buscarOuFalhar(idUsuario);
        
        if(usuario.getSenha().equals(senhaAntiga)){ 
            usuario.setSenha(novaSenha);
            
        }else{
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        
    }

    public Usuario buscarOuFalhar(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException(id));
    }
}
