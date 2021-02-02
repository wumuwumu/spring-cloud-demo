package cn.overhill.auth.resource;

import ch.qos.logback.core.rolling.helper.TokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


@Configuration
@EnableResourceServer
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {

    private static final String SOURCE_ID = "all";


    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter tokenEnhancer;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(SOURCE_ID).stateless(true);
        resources.tokenServices(defaultTokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        // 我们这里放开/order/*的请求，以/order/*开头的请求不用认证
        http.authorizeRequests().antMatchers("/order/*").permitAll().and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated();
        // @formatter:on
    }





    /**
     * 创建一个默认的资源服务token
     */
    @Bean
    public ResourceServerTokenServices defaultTokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // 使用自定义的Token转换器
        defaultTokenServices.setTokenEnhancer(tokenEnhancer);
        // 使用自定义的tokenStore
        defaultTokenServices.setTokenStore(tokenStore);
        return defaultTokenServices;
    }
}