package com.personal.cl.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.*;

@Documented
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@JsonSerialize(using = SecuritySerializeImpl.class)
@JacksonAnnotationsInside
public @interface SecuritySerialize {}
