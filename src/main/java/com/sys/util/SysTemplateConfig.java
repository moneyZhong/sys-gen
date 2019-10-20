package  com.sys.util;

import com.baomidou.mybatisplus.generator.config.TemplateConfig;

public class SysTemplateConfig extends TemplateConfig {
    private String vo = "/templates/vo.java";

    private String voConvert = "/templates/vo-convert.java";



    public SysTemplateConfig() {
    }

    public static SysTemplateConfig getInstance(TemplateConfig tmp){
        SysTemplateConfig t = new SysTemplateConfig();
        t.setXml(tmp.getXml());
        t.setController(tmp.getController());
        t.setEntity(tmp.getEntity(false));
        t.setMapper(tmp.getMapper());
        t.setEntityKt(tmp.getEntityKt());
        t.setService(tmp.getService());
        t.setServiceImpl(tmp.getServiceImpl());

        return t;

    }

    public String getVo() {
        return vo;
    }

    public void setVo(String vo) {
        this.vo = vo;
    }

    public String getVoConvert() {
        return voConvert;
    }

    public void setVoConvert(String voConvert) {
        this.voConvert = voConvert;
    }
}
