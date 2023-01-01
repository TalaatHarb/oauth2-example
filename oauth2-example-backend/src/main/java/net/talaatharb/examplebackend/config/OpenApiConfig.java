package net.talaatharb.examplebackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.*;

@OpenAPIDefinition(info = @Info(title = "Example backend", description = "Example backend", version = "1.0")
        , security = {@SecurityRequirement(name = "OAuth2")})
@SecurityScheme(name = "OAuth2", type = SecuritySchemeType.OAUTH2, scheme = "bearer", bearerFormat = "JWT",
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(authorizationUrl = "${keycloak.auth-url}", tokenUrl = "${keycloak.token-url}", scopes = {
                @OAuthScope(name = "openid", description = "openid"),
                @OAuthScope(name = "profile", description = "read scope"),
                @OAuthScope(name = "email", description = "email scope")
        })))
public class OpenApiConfig {

    OpenApiConfig() {
    }
}
