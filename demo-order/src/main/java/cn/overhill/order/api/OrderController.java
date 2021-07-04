package cn.overhill.order.api;

import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wumu
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @PostMapping
    public void addOrder(Integer money){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("产品价格："+money);
    }
}
