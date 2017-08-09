package com.asuper.aidldemo.annotaion;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by super on 2017/8/9.
 */

public class Fruit {
    @FruitAnno(name = "apple", color = "red")
    private String apple;
    @FruitAnno(name = "watermelon", color = "green")
    private String watermelon;

    public String getApple() {
        return apple;
    }

    public void setApple(String apple) {
        this.apple = apple;
    }

    public String getWatermelon() {
        return watermelon;
    }

    public void setWatermelon(String watermelon) {
        this.watermelon = watermelon;
    }

    public static void main(String[] args) {
        try {
            for(Field field : Fruit.class
                    .getDeclaredFields()) {
//                System.out.println("name =" + method.getName());
//                if (method.isAnnotationPresent(FruitAnno.class)) {
//                    try {
//                        for (Annotation annotation : method.getAnnotations()) {
//                            System.out.println("name =" + method.getName());
//                            System.out.println("anno =" + annotation);
//                        }
//                    } catch (Throwable ex) {
//                        ex.printStackTrace();
//                    }
//                }
                Class cls = Class.forName("com.asuper.aidldemo.annotaion.FruitAnno");
                if (field.isAnnotationPresent(cls)) {
                    try {
                        for (Annotation annotation : field.getAnnotations()) {
                            System.out.println("anno =" + annotation);
                        }
                        FruitAnno anno = field.getAnnotation(FruitAnno.class);

                        System.out.println("anno color " + anno.color());
                        System.out.println("anno name " + anno.name());

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
