package net.talaatharb.examplebackend.config;

import net.talaatharb.examplebackend.mappers.TodoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperBeansConfig {

    @Bean
    public TodoMapper todoMapper(){
        return Mappers.getMapper(TodoMapper.class);
    }
}
