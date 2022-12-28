package com.bitpuzzle.invoicegenerator.configuration;

import com.bitpuzzle.invoicegenerator.model.StringToClientConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToCategoryConverter());
        WebMvcConfigurer.super.addFormatters(registry);
    }

    @Bean
    public StringToClientConverter stringToCategoryConverter(){
        return new StringToClientConverter();
    }
}
