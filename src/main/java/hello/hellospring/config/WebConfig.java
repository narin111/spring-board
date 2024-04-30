package hello.hellospring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // CORS를 적용할 경로 지정
                .allowedOrigins("http://127.0.0.1:5173") // 요청을 허용할 Origin 지정
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // 허용할 HTTP 메소드 지정
    }
}