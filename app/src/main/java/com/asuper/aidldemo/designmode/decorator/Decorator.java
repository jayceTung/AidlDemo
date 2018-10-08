package com.asuper.aidldemo.designmode.decorator;

/**
 * @author super
 * @date 2018/10/8
 */
public class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void biu() {
        this.component.biu();
    }
}
