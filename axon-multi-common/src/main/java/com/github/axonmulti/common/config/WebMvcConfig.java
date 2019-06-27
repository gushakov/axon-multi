package com.github.axonmulti.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.debug("[Web MVC][Configuration] Adding String to UUID HTTP message converter");
        converters.add(new AbstractHttpMessageConverter<UUID>() {
            @Override
            protected boolean supports(Class<?> clazz) {
                return UUID.class.isAssignableFrom(clazz);
            }

            @Override
            protected UUID readInternal(Class<? extends UUID> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
                return UUID.fromString(StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8));
            }

            @Override
            protected void writeInternal(UUID uuid, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                outputMessage.getBody().write(uuid.toString().getBytes(StandardCharsets.UTF_8));
            }
        });
    }

}
