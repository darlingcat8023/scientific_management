package com.personal.cl.exception;

import lombok.Getter;

/**
 * @author liujiajun
 * @date 4/1/22
 */
@Getter
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
