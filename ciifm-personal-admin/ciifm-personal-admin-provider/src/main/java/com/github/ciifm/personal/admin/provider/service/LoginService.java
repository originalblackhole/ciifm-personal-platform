package com.github.ciifm.personal.admin.provider.service;

import com.github.ciifm.handy.model.ResponseData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoginService {

    void login(HttpServletRequest request);

    ResponseData<Void> auth(HttpServletRequest request, HttpServletResponse response);

    void createImgCode(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
