package com.personal.cl.service;

import com.personal.cl.dao.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liujiajun
 * @date 4/6/22
 */
@Service
public class LoginService {

    @Autowired
    private UserAccountRepository userAccountRepository;



}
