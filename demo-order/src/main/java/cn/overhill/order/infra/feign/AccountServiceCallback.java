package cn.overhill.order.infra.feign;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceCallback implements AccountService {
    @Override
    public ResponseEntity reduce(int money) {
        return null;
    }
}
