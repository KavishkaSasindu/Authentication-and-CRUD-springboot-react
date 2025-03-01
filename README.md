# Authentication-crud-fullstack-springboot-react
Demo project for createing fullstack web application for authentication and crud operation using sringboot react and postgresql

### cors configuration
```
@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}


```

### also add this to security config file

```
.cors(Customizer.withDefaults())
```
