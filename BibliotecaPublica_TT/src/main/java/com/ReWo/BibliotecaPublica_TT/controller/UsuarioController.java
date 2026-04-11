package com.ReWo.BibliotecaPublica_TT.controller;

import com.ReWo.BibliotecaPublica_TT.entity.Usuario;
import com.ReWo.BibliotecaPublica_TT.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController
{
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos()
    {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }
    @GetMapping("/email")
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email)
    {
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
    }
    @PostMapping
    public ResponseEntity<Usuario> guarda(@RequestBody Usuario usuario)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizaar(@PathVariable Long id, @RequestBody Usuario usuarioActualizado)
    {
        return ResponseEntity.ok(usuarioService.actualizar(id, usuarioActualizado));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id)
    {
        usuarioService.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado Exitosamente");
    }
}
