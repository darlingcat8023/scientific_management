package com.personal.cl.aspect;

import java.lang.annotation.*;

/**
 * @author xiaowenrou
 * @date 4/6/22
 */
@Documented
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TokenCheck {

    TokenType value() default TokenType.USER;

    enum TokenType {

        /**
         * 普通用户
         */
        USER,

        /**
         * 管理员
         */
        ADMIN;

    }

}
