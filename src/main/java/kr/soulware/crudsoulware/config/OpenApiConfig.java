package kr.soulware.crudsoulware.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI createOpenApi() {
        return new OpenAPI()
                .info(getInfo())
                .addServersItem(getDeployServer())
//                .addServersItem(getLocalServer())
                .components(new Components());
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
