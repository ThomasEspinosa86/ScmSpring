package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import com.example.demo.Dto.UsuarioDTO;
import com.example.demo.repository.RolRepositorio;
import com.example.demo.repository.UsuarioRepositorio;
import com.example.demo.modelo.Rol;
import com.example.demo.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Optional<Rol> rolOptional = rolRepositorio.findById(usuarioDTO.getIdRol());
        if (rolOptional.isEmpty()) {
            throw new IllegalArgumentException("Rol no encontrado con el ID: " + usuarioDTO.getIdRol());
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuarioDTO.getNombre());
        nuevoUsuario.setApellido(usuarioDTO.getApellido());
        nuevoUsuario.setCorreo(usuarioDTO.getCorreo());
        nuevoUsuario.setContrasena(usuarioDTO.getContrasena());
        nuevoUsuario.setTelefono(usuarioDTO.getTelefono());
        nuevoUsuario.setDireccion(usuarioDTO.getDireccion());
        nuevoUsuario.setRol(rolOptional.get());
        return usuarioRepositorio.save(nuevoUsuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> obtenerPorId(Integer id) {
        return usuarioRepositorio.findById(id);
    }

    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return Optional.ofNullable(usuarioRepositorio.findByCorreo(correo));
    }

    public Usuario actualizarUsuario(Integer id, UsuarioDTO usuarioDTO) {
        return usuarioRepositorio.findById(id).map(usuario -> {
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setCorreo(usuarioDTO.getCorreo());
            usuario.setTelefono(usuarioDTO.getTelefono());
            usuario.setDireccion(usuarioDTO.getDireccion());
            // No se actualiza la contraseña con este método por seguridad

            Optional<Rol> rolOptional = rolRepositorio.findById(usuarioDTO.getIdRol());
            rolOptional.ifPresent(usuario::setRol);

            return usuarioRepositorio.save(usuario);
        }).orElse(null);
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepositorio.deleteById(id);
    }
}
