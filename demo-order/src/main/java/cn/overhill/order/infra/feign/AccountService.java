package cn.overhill.order.infra.feign;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "demo-user",fallback = AccountServiceCallback.class)
public interface AccountService {

    @PostMapping(value = "/account/reduce")
    ResponseEntity reduce(@RequestParam int money);
}
