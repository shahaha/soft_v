package com.skylab.soft_v.component;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionLog {
    String value() default "";
}
