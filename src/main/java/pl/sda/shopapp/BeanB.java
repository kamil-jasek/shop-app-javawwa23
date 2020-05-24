package pl.sda.shopapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class BeanB {

    private final BeanA beanA;
    private final BeanC beanC;

    BeanB(BeanA beanA, BeanC beanC) {
        this.beanA = beanA;
        this.beanC = beanC;
    }

    void sayHello() {
        System.out.println("I'm in BeanB");
        System.out.println("I'm invoking BeanA");
        beanA.sayHello();
        beanC.whoIam();
    }

//    @Autowired
//    public void setBeanC(BeanC beanC) {
//        this.beanC = beanC;
//    }
}
