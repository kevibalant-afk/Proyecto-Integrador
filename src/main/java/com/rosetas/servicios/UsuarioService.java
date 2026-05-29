package com.rosetas.servicios;

import java.util.Optional;

import com.rosetas.Dao.DaoUsuario;
import com.rosetas.modelo.Usuario;

public class UsuarioService {

    private final DaoUsuario daoUsuario;

    public UsuarioService(DaoUsuario daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public boolean login(String username, String password) {

        Optional<Usuario> usuarioOpt =
                daoUsuario.buscarPorUsername(username);

        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get().getPassword().equals(password);
        }

        return false;
    }

    public void registrar(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Campos vacíos");
        }

        if (daoUsuario.buscarPorUsername(username).isPresent()) {
            throw new IllegalArgumentException("Usuario ya existe");
        }

        daoUsuario.guardar(new Usuario(username, password));
    }
}