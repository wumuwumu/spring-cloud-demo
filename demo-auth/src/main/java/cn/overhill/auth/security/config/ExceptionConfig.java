package cn.overhill.auth.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionConfig {


        @ResponseBody
        @ExceptionHandler(value = OAuth2Exception.class)
        public Map<String,Object> handleOauth2(OAuth2Exception e) {
            Map map = new HashMap<String,Object>();
            map.put("code",0);
            map.put("message",e.getMessage());
            return map;
        }


}
