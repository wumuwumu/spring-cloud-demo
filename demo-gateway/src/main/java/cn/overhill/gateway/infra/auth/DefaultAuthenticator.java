package cn.overhill.gateway.infra.auth;

import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import sun.misc.Request;

import java.nio.charset.StandardCharsets;
import java.util.List;


@Component
public class DefaultAuthenticator  implements Authenticator{

    @Autowired
    List<AuthHelper> authHelperList;

    @Override
    public RequestContext auth(ServerWebExchange exchange) {
        RequestContext requestContext = new RequestContext();
        requestContext.setExchange(exchange);
        for(AuthHelper authHelper : authHelperList){
            if(authHelper.shouldFilter(exchange)){
                boolean en = authHelper.filter(requestContext);
                if(!en){
                    break;
                }
            }
        }
        return  requestContext;
    }
}
