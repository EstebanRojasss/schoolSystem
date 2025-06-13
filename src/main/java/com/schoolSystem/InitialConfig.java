package com.schoolSystem;

import com.schoolSystem.entities.rol.Rol;
import com.schoolSystem.entities.rol.TipoRol;
import com.schoolSystem.repository.RolRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class InitialConfig {


    private final RolRepository rolRepository;

    public InitialConfig(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }


    @PostConstruct
    public void cargarDatosIniciales() {
        for (TipoRol tipoRol : TipoRol.values()) {
            Rol rol = new Rol();
            rol.setTipoRol(tipoRol);
            rolRepository.save(rol);
        }
    }


}
