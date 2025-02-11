package net.talaatharb.examplebackend.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;

import net.talaatharb.examplebackend.mappers.TodoMapper;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
public class MapperBeansConfig {

    MapperBeansConfig() {
    }

    @Bean
    TodoMapper todoMapper() {
        return Mappers.getMapper(TodoMapper.class);
    }
}
