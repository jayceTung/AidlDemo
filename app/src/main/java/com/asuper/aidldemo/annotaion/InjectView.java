package com.asuper.aidldemo.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.ElementType.FIELD;

/**
 * Created by super on 2017/8/8.
 */
@Retention(CLASS) @Target(FIELD) @Documented
public @interface InjectView {

    int value() default 1;

    String getDefault() default "string";
}
