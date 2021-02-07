package com.ly.annotation.postconstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * liyang 2021-01-15
 * 注入时机：Constructor >> @Autowired >> @PostConstruct
 *
 * 启动springboot项目的时候加载，因此暂时放在这，等下次打开springboot项目后验证下
 */
@Component
public class ObjectA {
    @Autowired
    private ObjectB ob;

    public ObjectA() {
        System.out.println("此时b还未被注入: ob = " + ob);
    }

    @PostConstruct
    private void init() {
        System.out.println("@PostConstruct将在依赖注入完成后被自动调用: ob = " + ob);
    }

    public ObjectB getOb() {
        return ob;
    }

    public void setOb(ObjectB ob) {
        this.ob = ob;
    }
}
