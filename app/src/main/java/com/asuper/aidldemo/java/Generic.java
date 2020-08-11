package com.asuper.aidldemo.java;

/**
 * @author super
 * @date 2020/8/11
 */
public class Generic {

    public static void main(String[] args) {
        Plate<? extends Fruit> applePlate = new Plate<Apple>(new Apple(), String.class);
        Plate<? super Apple> p = new Plate<>(new Apple(), String.class);
        p.set(new GreenApple());
        GreenApple apple = (GreenApple) p.get();

        add(new Plate<Apple>(new Apple(), String.class), new GreenApple());
    }

    public static int add(Plate<? super Apple> fruit, GreenApple apple) {
        fruit.set(apple);
        return 0;
    }
}
