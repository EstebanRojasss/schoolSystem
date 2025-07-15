package com.schoolSystem.mapper.mapperInterfaces;

import com.schoolSystem.entities.Curso;

public interface IUpdate<Entity, Input> {
      void update(Entity updated, Input input, Curso curso);
}
