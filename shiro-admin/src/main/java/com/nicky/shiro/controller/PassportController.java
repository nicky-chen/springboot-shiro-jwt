package com.nicky.shiro.controller;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.nicky.shiro.framework.object.ResponseVO;
import com.nicky.shiro.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 登录相关
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/passport")
@Slf4j
public class PassportController {

    @GetMapping("/login")
    public ModelAndView login(Model model) {
        Subject subject = SecurityUtils.getSubject();
        //判断是否登录记录获取权限认真通过
        if (subject.isAuthenticated()||subject.isRemembered()){
            return ResultUtil.redirect("/index");
        }
        return ResultUtil.view("/login");
    }

    /**
     * 登录
     */
    @PostMapping("/signin")
    @ResponseBody
    public ResponseVO submitLogin(String username, String password, Boolean rememberMe, String kaptcha) {

        try {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(username), "用户名不能为空");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "密码为空");
            Preconditions.checkNotNull(rememberMe, "是否记住我不能为空");
        } catch (Exception e) {
            return ResultUtil.error(e.getMessage());
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到xxRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
            return ResultUtil.success("登录成功！");
        } catch (Exception e) {
            log.error("登录失败，用户名[{}]", username, e);
            token.clear();
            return ResultUtil.error(e.getMessage());
        }
    }

    /**
     * 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
     */
    @GetMapping("/logout")
    public ModelAndView logout(RedirectAttributes redirectAttributes) {
        // http://www.oschina.net/question/99751_91561
        // 此处有坑： 退出登录，其实不用实现任何东西，只需要保留这个接口即可，也不可能通过下方的代码进行退出
        // SecurityUtils.getSubject().logout();
        // 因为退出操作是由Shiro控制的
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return ResultUtil.redirect("index");
    }
}
