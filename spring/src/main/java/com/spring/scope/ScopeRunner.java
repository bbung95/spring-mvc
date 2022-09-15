package com.spring.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

//@Component
public class ScopeRunner implements ApplicationRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("---------------------------------");
        System.out.println("Singleton");
        System.out.println("---------------------------------");
        System.out.println(applicationContext.getBean(Single.class));
        System.out.println(applicationContext.getBean(Single.class));
        System.out.println(applicationContext.getBean(Single.class));
        System.out.println("---------------------------------");
        System.out.println("ProtoType");
        System.out.println("---------------------------------");
        System.out.println(applicationContext.getBean(Proto.class));
        System.out.println(applicationContext.getBean(Proto.class));
        System.out.println(applicationContext.getBean(Proto.class));
        System.out.println("---------------------------------");
        System.out.println("Proto by Single");
        System.out.println("---------------------------------");
        System.out.println(applicationContext.getBean(Single.class).getProto());
        System.out.println(applicationContext.getBean(Single.class).getProto());
        System.out.println(applicationContext.getBean(Single.class).getProto());
    }
}
