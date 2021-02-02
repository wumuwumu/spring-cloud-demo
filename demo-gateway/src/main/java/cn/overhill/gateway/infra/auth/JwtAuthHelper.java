package cn.overhill.gateway.infra.auth;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class JwtAuthHelper implements AuthHelper, Ordered {

    @Override
    public boolean shouldFilter(ServerWebExchange exchange) {
        return true;
    }

    @Override
    public boolean filter(RequestContext requestContext) {
      HttpHeaders httpHeaders =  requestContext.getExchange().getRequest().getHeaders();
      List<String> header = httpHeaders.get("Authorization");
      if(header.size()>0){
          String token = header.get(0).substring(7);
          // 获取用户信息
          // 生成jwt
            String jwt = buildJWT("wumu");
          ServerHttpRequest serverHttpRequest = requestContext.getExchange().getRequest().mutate().header("Authorization", "Bearer "+jwt).build();

          requestContext.getExchange().mutate().request(serverHttpRequest).build();
      }
        return true;
    }

    /**
     * 生成Token
     * @param account
     * @return
     */
    public static String buildJWT(String account) {
        try {
            /**
             * 1.创建一个32-byte的密匙
             */
            MACSigner macSigner = new MACSigner("yrtu1h4efhax9bum8nbcw2jh3vt83hh1xviawrgricbrcnthoruzvlw65i6kdpn7wdwppstg887uov1e2hr3nl070zq9vygrnouhcspczf51ckp8uv4cls4euyqr06uh8j8xdeb6i0n4pvbd7zk9ivbe0vqsqtifft347pr61z5r54ridrlwl9iauir6g0n8ec9xjp4yuqxe82ua1uqz3fud7yx09iodwp46qw7p95nuh42o1b345f6696wke3md");
            /**
             * 2. 建立payload 载体
             */
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("doi")
                    .issuer("http://www.overhill.cn")
                    .expirationTime(new Date(System.currentTimeMillis() + 1000*60*60))
                    .claim("ACCOUNT",account)
                    .build();

            /**
             * 3. 建立签名
             */
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(macSigner);

            /**
             * 4. 生成token
             */
            String token = signedJWT.serialize();
            return token;
        } catch (KeyLengthException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 90;
    }
}
