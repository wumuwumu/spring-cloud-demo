package cn.overhill.user.cmd;

import cn.overhill.starter.aop.AOPProperties;
import cn.overhill.starter.aop.EnableAOP;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class JsonCommander implements CommandLineRunner {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    AOPProperties aopProperties;

    @Override
    public void run(String... args) throws Exception {

        String result = objectMapper.writeValueAsString(aopProperties);
        log.info("运行序列化结果：{}",result);
    }
}
