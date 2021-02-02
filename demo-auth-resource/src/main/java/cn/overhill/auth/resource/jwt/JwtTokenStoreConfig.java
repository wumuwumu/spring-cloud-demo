package cn.overhill.auth.resource.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtTokenStoreConfig {

    @Bean
    @Primary
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 配置jwt使用的密钥
        jwtAccessTokenConverter.setSigningKey("yrtu1h4efhax9bum8nbcw2jh3vt83hh1xviawrgricbrcnthoruzvlw65i6kdpn7wdwppstg887uov1e2hr3nl070zq9vygrnouhcspczf51ckp8uv4cls4euyqr06uh8j8xdeb6i0n4pvbd7zk9ivbe0vqsqtifft347pr61z5r54ridrlwl9iauir6g0n8ec9xjp4yuqxe82ua1uqz3fud7yx09iodwp46qw7p95nuh42o1b345f6696wke3md");
        return jwtAccessTokenConverter;
    }

}
