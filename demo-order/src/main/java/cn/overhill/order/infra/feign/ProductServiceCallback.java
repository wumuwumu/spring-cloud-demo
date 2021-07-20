package cn.overhill.order.infra.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceCallback implements ProductService {
    @Override
    public ResponseEntity reduce(int number) {
        return null;
    }
}
