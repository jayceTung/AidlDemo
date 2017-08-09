package com.asuper.aidldemo.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by super on 2017/8/9.
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.FIELD)
public @interface FruitAnno {

    String name() default "fruit";

    String color() default "blue";
}
