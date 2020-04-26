package StudentManagementSystem.web.controller;

import StudentManagementSystem.login.service.LoginService;
import StudentManagementSystem.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author hctrl
 * @date 2020/4/16 18:43
 */
@RestController
@RequestMapping("/api/")
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping("login")
    public ResultVo login(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password,@RequestParam(value = "role") String role){

        ResultVo resultVo = new ResultVo();

        if(loginService.login(username,password,role)){
            resultVo.setStatus(1);
        }
        return resultVo;
    }

}
