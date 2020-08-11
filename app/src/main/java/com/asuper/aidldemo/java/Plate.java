package com.asuper.aidldemo.java;

/**
 * @author super
 * @date 2020/8/11
 */
public class Plate<T> {
    private T item;

    public Plate(T t, Class clazz) {
        this.item = t;
        try {
            clazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void set(T t) {
        System.out.println(t.getClass());
        item = t;
    }

    public T get() {
        return item;
    }

    public static <K> Plate<K> create(K k) {
        return new Plate<K>(k, String.class);
    }
}
