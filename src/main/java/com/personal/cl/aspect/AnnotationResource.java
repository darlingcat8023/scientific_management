package com.personal.cl.aspect;

import org.springframework.aop.support.AopUtils;
import org.springframework.core.MethodClassKey;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
public class AnnotationResource<A extends Annotation> {

    public final Map<MethodClassKey, A> annotations = new ConcurrentHashMap<>();

    public final Class<A> annotationClass;

    public AnnotationResource(Class<A> annotation) {
        annotationClass = annotation;
    }

    public A getSource(@NonNull Method method, @NonNull Class<?> targetClass) {
        if (method.getDeclaringClass() == Object.class) {
            return null;
        }
        var cacheKey = this.getCacheKey(method, targetClass);
        var cached = this.annotations.get(cacheKey);
        if (cached != null) {
            return cached;
        }
        var annotation = this.computeOperations(method, targetClass);
        if (annotation != null) {
            this.annotations.put(cacheKey, annotation);
        }
        return annotation;
    }

    private A computeOperations(Method method, @Nullable Class<?> targetClass) {
        if (!Modifier.isPublic(method.getModifiers())) {
            return null;
        } else {
            var specificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            A annotation = this.findAnnotation(specificMethod);
            if (annotation != null) {
                return annotation;
            } else {
                annotation = this.findAnnotation(specificMethod.getDeclaringClass());
                if (annotation != null && ClassUtils.isUserLevelMethod(method)) {
                    return annotation;
                } else {
                    if (specificMethod != method) {
                        annotation = this.findAnnotation(method);
                        if (annotation != null) {
                            return annotation;
                        }
                        annotation = this.findAnnotation(method.getDeclaringClass());
                        if (annotation != null && ClassUtils.isUserLevelMethod(method)) {
                            return annotation;
                        }
                    }
                    return null;
                }
            }
        }
    }

    protected MethodClassKey getCacheKey(Method method, @Nullable Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }

    protected A findAnnotation(Method method) {
        if (method.isAnnotationPresent(this.annotationClass)) {
            return  method.getDeclaredAnnotation(this.annotationClass);
        }
        return null;
    }

    protected A findAnnotation(Class<?> clazz) {
        if (clazz.isAnnotationPresent(this.annotationClass)) {
            return clazz.getDeclaredAnnotation(this.annotationClass);
        }
        return null;
    }

}
