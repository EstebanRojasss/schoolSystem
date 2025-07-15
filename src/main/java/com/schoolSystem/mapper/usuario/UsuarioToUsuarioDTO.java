package com.schoolSystem.mapper;
import com.schoolSystem.dto.usuarioDto.UsuarioGetDto;
import com.schoolSystem.entities.Usuario;
import com.schoolSystem.mapper.mapperInterfaces.IMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioToUsuarioDTO implements IMapper<Usuario, UsuarioGetDto> {

    @Override
    public UsuarioGetDto map(Usuario in) {
        return new UsuarioGetDto(
                in.getNombreUsuario(),
                in.getEmail(),
                in.getFechaRegistro() );
    }
}
