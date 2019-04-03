package org.productshop.config;

import org.modelmapper.ModelMapper;
import org.productshop.mapings.MappingsInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBeanConfiguration {

    static ModelMapper mapper;
    static {
        mapper = new ModelMapper();
        MappingsInitializer.initMappings(mapper);
    }
    @Bean
    public ModelMapper modelMapper(){
        return mapper;
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
