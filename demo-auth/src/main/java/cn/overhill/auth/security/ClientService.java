package cn.overhill.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class ClientService implements ClientDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    //        clients.inMemory()
//                .withClient("admin")//配置client_id
//                .secret()//配置client_secret
//                .accessTokenValiditySeconds(3600)//配置访问token的有效期
//                .refreshTokenValiditySeconds(864000)//配置刷新token的有效期
//                .redirectUris("http://www.baidu.com")//配置redirect_uri，用于授权成功后跳转
//                .scopes("all")//配置申请的权限范围
//                .authorizedGrantTypes("authorization_code","password");//配置grant_type，表示授权类型
    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId("admin");
        baseClientDetails.setClientSecret(passwordEncoder.encode("admin123456"));
        baseClientDetails.setRefreshTokenValiditySeconds(864000);
        baseClientDetails.setAccessTokenValiditySeconds(3600);
        baseClientDetails.setRegisteredRedirectUri(Collections.singleton("http://www.baidu.com"));
        baseClientDetails.setScope(Collections.singleton("all"));
        baseClientDetails.setAuthorizedGrantTypes(Arrays.asList("authorization_code","refresh_token","password"));
        return baseClientDetails;
    }
}
