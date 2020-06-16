package kiec.ireneusz.germanelearningplatformserver.config;


import kiec.ireneusz.germanelearningplatformserver.domain.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthenticationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final LoginService userDetailsService;
    private DataSource dataSource;
    private static TokenStore tokenStore = new InMemoryTokenStore();

    @Autowired
    public AuthenticationServerConfig(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            LoginService userDetailsService,
            DataSource dataSource
    ) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.dataSource = dataSource;
    }

//    @Bean
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(this.dataSource);
//    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("browser")
                .authorizedGrantTypes("refresh_token", "password")
                .accessTokenValiditySeconds(8 * 3600)
                .refreshTokenValiditySeconds(7 * 24 * 3600)
                .secret(passwordEncoder.encode(""))
                .scopes("ui");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .pathMapping("/oauth/token", "/auth/refresh")
                .pathMapping("/oauth/check_token", "/auth/check_token")
                .pathMapping("/oauth/authorize", "/auth/authorize")
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

}
