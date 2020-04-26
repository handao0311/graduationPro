package StudentManagementSystem.login.service;

import StudentManagementSystem.login.dao.LoginDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hctrl
 * @date 2020/4/16 18:16
 */
@Service
public class LoginService {

    @Resource
    private LoginDao loginDao;

    public boolean login(String username,String password,String role){

        return loginDao.login(username,password,role);
    }



}
