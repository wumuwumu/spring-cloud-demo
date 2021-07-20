package cn.overhill.swagger;


import com.github.xiaoymin.knife4j.core.extend.OpenApiExtendSetting;
import com.github.xiaoymin.knife4j.core.model.MarkdownProperty;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jHttpBasic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(
        prefix = "knife4j"
)
public class Knife4jProperties {
    private boolean enable = false;
    private boolean cors = false;
    private Knife4jHttpBasic basic;
    private boolean production = false;
    private OpenApiExtendSetting setting;
    private List<MarkdownProperty> documents;

    public Knife4jProperties() {
    }

    public Knife4jHttpBasic getBasic() {
        return this.basic;
    }

    public void setBasic(Knife4jHttpBasic basic) {
        this.basic = basic;
    }

    public boolean isProduction() {
        return this.production;
    }

    public void setProduction(boolean production) {
        this.production = production;
    }

    public List<MarkdownProperty> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<MarkdownProperty> documents) {
        this.documents = documents;
    }

    public OpenApiExtendSetting getSetting() {
        return this.setting;
    }

    public void setSetting(OpenApiExtendSetting setting) {
        this.setting = setting;
    }

    public boolean isCors() {
        return this.cors;
    }

    public void setCors(boolean cors) {
        this.cors = cors;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

