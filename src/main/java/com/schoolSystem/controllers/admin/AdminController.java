package com.schoolSystem.controllers.admin;

import com.schoolSystem.dto.usuarioDto.UsuarioCreateDto;
import com.schoolSystem.dto.usuarioDto.UsuarioGetDto;
import com.schoolSystem.dto.usuarioDto.UsuarioUpdateDto;
import com.schoolSystem.mapper.usuario.UsuarioToUsuarioDTO;
import com.schoolSystem.service.admin.AdminUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/admin")
public class AdminController {

    private final AdminUsuarioService adminUsuarioService;

    private final UsuarioToUsuarioDTO mapUsuarioDto;

    public AdminController(AdminUsuarioService adminUsuarioService, UsuarioToUsuarioDTO mapUsuarioDto) {
        this.adminUsuarioService = adminUsuarioService;
        this.mapUsuarioDto = mapUsuarioDto;
    }

    @PostMapping("/user")
    @Operation(description = "Endpoint encargado de crear nuevos Usuarios")
    public ResponseEntity<Void> createUser(@RequestBody UsuarioCreateDto usuarioCreateDto){
        adminUsuarioService.createUser(usuarioCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    @Operation(description = "Endpoint encargado de listar todos los usuarios")
    public ResponseEntity<Set<UsuarioGetDto>>getAllUsers(){
        return ResponseEntity.ok(adminUsuarioService.getAllUsers().
                stream().
                map(mapUsuarioDto::map).
                collect(Collectors.toSet()));
    }

    @DeleteMapping("/user")
    @Operation(description = "Endpoint encargado de borrar usuario por email.")
    public ResponseEntity<String> deleteUserByEmail(String email){
        adminUsuarioService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("user/{id}")
    @Operation(description = "Endpoint de borrar usuario por Id")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id){
        adminUsuarioService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("user/{id}")
    @Operation(description = "Endpoint encargado de actualizar usuario por Id")
    public ResponseEntity<String> updateUserById(@PathVariable("id") Long id, @RequestBody UsuarioUpdateDto usuarioUpdateDto){
        adminUsuarioService.updateById(id, usuarioUpdateDto);
        return ResponseEntity.ok().build();
    }






}
