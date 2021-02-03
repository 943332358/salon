package org.salon.salonadmin.login.github.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangxin
 * @date 2021/2/3 17:48
 * @since V1.0
 */
@Controller
@RequestMapping("oath")
public class GithubLoginController {

    @RequestMapping("redirect")
    public void redirect() {

    }
}
