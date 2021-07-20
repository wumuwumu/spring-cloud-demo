package cn.overhill.user.api;

import cn.overhill.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping(value = "/reduce")
    public ResponseEntity reduce(@RequestParam("money")  int money){
        accountService.reduce(money);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "")
    public int get(){
        return accountService.get();
    }
}
