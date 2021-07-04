package cn.overhill.user.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/sse")
public class SSEController {

    @RequestMapping(value = "/get", produces = "text/event-stream;charset=UTF-8")
    public String getData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:行情:" + Math.random() + "\n\n";
    }

    private static Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();


    @GetMapping(path = "subscribe")
    public SseEmitter subscribe(String id) {
        // 超时时间设置为1小时
        SseEmitter sseEmitter = new SseEmitter(3600_000L);
        sseCache.put(id, sseEmitter);
        sseEmitter.onTimeout(() -> sseCache.remove(id));
        sseEmitter.onCompletion(() -> System.out.println("完成！！！"));
        return sseEmitter;
    }

    @GetMapping(path = "push")
    public String push(String id, String content) throws IOException {
        SseEmitter sseEmitter = sseCache.get(id);
        if (sseEmitter != null) {
            sseEmitter.send(content);
        }
        return "over";
    }

    @GetMapping(path = "over")
    public String over(String id) {
        SseEmitter sseEmitter = sseCache.get(id);
        if (sseEmitter != null) {
            sseEmitter.complete();
            sseCache.remove(id);
        }
        return "over";
    }

}