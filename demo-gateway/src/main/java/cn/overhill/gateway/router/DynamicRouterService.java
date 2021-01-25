package cn.overhill.gateway.router;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class DynamicRouterService implements ApplicationEventPublisherAware {

    ApplicationEventPublisher publisher ;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void notifyRouter(){
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }
}
