package kr.soulware.crudsoulware.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private final String AUTH_TOKEN_HEADER = "Authorization";

    @Bean
    public OpenAPI createOpenApi() {
        return new OpenAPI()
                .info(getInfo())
                .addServersItem(getDeployServer())
                .addServersItem(getLocalServer())
                .addSecurityItem(new SecurityRequirement().addList(AUTH_TOKEN_HEADER))
                .components(new Components()
                        .addSecuritySchemes(AUTH_TOKEN_HEADER, new SecurityScheme()
                                .name(AUTH_TOKEN_HEADER)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("Bearer")));
    }

    private Info getInfo() {
        return new Info()
                .title("crud-soulware-ben API Documents")
                .version("v1")
                .description("crud-soulware-ben API 명세");
    }

    private Server getLocalServer() {
        return new Server().url("http://localhost:8080")
                .description("Local Server");
    }

    private Server getDeployServer() {
        return new Server().url("https://bensoulware.store")
                .description("Deploy Server");
    }
}
