package StudentManagementSystem.login.dao;

import StudentManagementSystem.login.dto.ManagerLogin;
import StudentManagementSystem.login.dto.StudentLogin;
import StudentManagementSystem.login.dto.TeacherLogin;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

/**
 * @author hctrl
 * @date 2020/4/17 0:38
 */
@Repository
public class LoginDao {


    @Resource
    private MongoTemplate mongoTemplate;

    public boolean login(String username, String password, String role) {

        if ("student".equals(role)) {
            Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
            StudentLogin studentLogin = mongoTemplate.findOne(query, StudentLogin.class);

            if (studentLogin != null) {
                return true;
            }
        } else if ("teacher".equals(role)) {

            Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
            TeacherLogin teacherLogin = mongoTemplate.findOne(query, TeacherLogin.class);

            if (teacherLogin != null){
                return true;
            }
        } else {
            Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
            ManagerLogin managerLogin = mongoTemplate.findOne(query, ManagerLogin.class);

            if (managerLogin != null){
                return true;
            }
        }

        return false;
    }

}
