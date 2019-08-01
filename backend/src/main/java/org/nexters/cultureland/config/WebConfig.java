package org.nexters.cultureland.config;

import org.nexters.cultureland.common.JwtServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
<<<<<<< HEAD
/*        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/signInOrUp", "/error");*/
=======
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/signInOrUp", "/error");
>>>>>>> 70dcb2ed5f100a7f957668c6b2e51e47645c6064
    }

    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor(new JwtServiceImpl());
    }
}
