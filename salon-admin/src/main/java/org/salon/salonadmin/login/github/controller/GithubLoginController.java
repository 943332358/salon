package org.salon.salonadmin.login.github.controller;

import org.salon.salonadmin.login.github.service.GithubLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author yangxin
 * @date 2021/2/3 17:48
 * @since V1.0
 */
@Controller
@RequestMapping("oath")
public class GithubLoginController {
    @Resource
    private GithubLoginService githubLoginService;

    @RequestMapping("redirect")
    public void redirect(String code) {
        githubLoginService.login(code);
    }
}
