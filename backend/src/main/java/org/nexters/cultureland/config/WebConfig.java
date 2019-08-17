package org.nexters.cultureland.config;

import org.nexters.cultureland.common.JwtManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArugmentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/signInOrUp", "/error");
    }

    @Bean
    public UserArugmentResolver userArugmentResolver() {
        return new UserArugmentResolver();
    }

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor(new JwtManager());
    }
}
