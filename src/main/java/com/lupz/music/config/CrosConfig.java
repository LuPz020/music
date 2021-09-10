package com.lupz.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: my-first-VueBoot
 * @description:跨域问题的解决
 * @author: LuPz
 * @create: 2021-06-18 20:21
 **/
@Configuration
public class CrosConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    };

}