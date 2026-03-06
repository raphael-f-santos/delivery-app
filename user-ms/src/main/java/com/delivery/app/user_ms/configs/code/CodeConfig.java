package com.delivery.app.user_ms.configs.code;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class CodeConfig {

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }

    @Bean
    public CodeGenerator codeGenerator(SecureRandom secureRandom) {
        return new CodeGenerator(secureRandom);
    }
}
