package com.personal.cl.utils;

import com.personal.cl.exception.BusinessException;

import javax.validation.Validator;

/**
 * @author xiaowenrou
 * @date 4/6/22
 */
public abstract class ValidatorUtils {

    /**
     * 参数校验
     * @param validator
     * @param object
     * @param groups
     */
    public static void valid(Validator validator, Object object, Class<?> ... groups) {
        if (validator == null) {
            throw new BusinessException("参数检查器获取失败");
        }
        var res = validator.validate(object, groups);
        res.stream().findFirst().ifPresent(constraintViolation -> {
            throw new BusinessException(constraintViolation.getMessage());
        });
    }

}
