package com.asuper.aidldemo.designmode.decorator;

/**
 * @author super
 * @date 2018/10/8
 */
public class ConcreateDecorator extends Decorator {

    public ConcreateDecorator(Component component) {
        super(component);
    }

    @Override
    public void biu() {
        System.out.println("ConcreateDecorator biu");
        this.component.biu();
    }
}
