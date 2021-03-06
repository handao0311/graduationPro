package StudentManagementSystem.student.dao;

import StudentManagementSystem.student.dto.StudentImage;
import StudentManagementSystem.student.dto.StudentPersonalInformation;
import StudentManagementSystem.student.dto.StudentScore;
import StudentManagementSystem.student.dto.StudentTeachingEval;
import StudentManagementSystem.vo.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author hctrl
 * @date 2020/4/20 23:49
 */
@Repository
public class StudentDao {


    @Resource
    private MongoTemplate mongoTemplate;

    public StudentPersonalInformation loadinfo(String loginname){
        //System.out.println(loginname);
        Query query = new Query(Criteria.where("loginname").is(loginname));
        StudentPersonalInformation studentPersonalInformation = mongoTemplate.findOne(query, StudentPersonalInformation.class);
        return studentPersonalInformation;
    }

    public boolean changeUsernameInfo(StudentPersonalInformation studentPersonalInformation){

        Query query = Query.query(Criteria.where("name").is(studentPersonalInformation.getName()));
        Update update = Update.update("nikeName", studentPersonalInformation.getNikeName())
                .set("QQ", studentPersonalInformation.getQq()).set("motto",studentPersonalInformation.getMotto());

        return mongoTemplate.updateFirst(query, update, StudentPersonalInformation.class).getN() == 1 ? true : false;

    }

    public boolean uploadImage(StudentImage studentImage){

        //先删除数据库中的相同数据
        Query query = Query.query(Criteria.where("studentID").is(studentImage.getStudentID()));
        mongoTemplate.remove(query,StudentImage.class);

        mongoTemplate.insert(studentImage,"studentImage");

        return true;
    }

    public String loadImage(String studentID){

        Query query = Query.query(Criteria.where("studentID").is(studentID));
        StudentImage studentImage = mongoTemplate.findOne(query, StudentImage.class);
        return studentImage.getImgUrl();
    }

    public List<StudentScore> getStudentScore(StudentScore studentScore){

        Query query = Query.query(Criteria.where("name").is(studentScore.getName()).and("year").is(studentScore.getYear() + "").
                and("team").is(studentScore.getTeam()));

        List<StudentScore> studentScoreTable = mongoTemplate.find(query,StudentScore.class);

        return studentScoreTable;
    }

    public boolean saveTeachingEval(List<StudentTeachingEval> studentTeachingEvals){

        mongoTemplate.insert(studentTeachingEvals,"studentTeachingEvaluationTable");

        return true;
    }

    public List<StudentTeachingEval> findTeachingEval(String name){

        Query query = Query.query(Criteria.where("studentName").is(name));

        List<StudentTeachingEval> studentTeachingEvals = mongoTemplate.find(query, StudentTeachingEval.class);
        System.out.println(studentTeachingEvals.size());
        return studentTeachingEvals;
    }

    public List<StudentPersonalInformation> getMailList(String name,String grade){

        Pattern pattern =Pattern.compile("^.*" + name + ".*$",Pattern.CASE_INSENSITIVE);
        Pattern pattern1 =Pattern.compile("^.*" + grade + ".*$",Pattern.CASE_INSENSITIVE);
        System.out.println(pattern.toString());
        System.out.println(pattern1.toString());
        Query query = new Query(Criteria.where("name").is(pattern).and("grade").is(pattern1));

        List<StudentPersonalInformation> studentPersonalInformationTable = mongoTemplate.find(query,StudentPersonalInformation.class,"studentPersonalInformationTable");

        return studentPersonalInformationTable;
    }

    public List<StudentPersonalInformation> getMailListPage(Page page,String name,String grade){

        Query query = new Query();

        query.skip((page.getCurrentPage() - 1) * page.getPageSize());
        query.limit(page.getPageSize());

        Pattern pattern =Pattern.compile("^.*" + name + ".*$",Pattern.CASE_INSENSITIVE);
        Pattern pattern1 =Pattern.compile("^.*" + grade + ".*$",Pattern.CASE_INSENSITIVE);

        query.addCriteria(Criteria.where("name").is(pattern).and("grade").is(pattern1));

        List<StudentPersonalInformation> studentPersonalInformationTable = mongoTemplate.find(query, StudentPersonalInformation.class, "studentPersonalInformationTable");

        for (StudentPersonalInformation studentPersonalInformation : studentPersonalInformationTable){
            System.out.println(studentPersonalInformation);
        }

        return studentPersonalInformationTable;
    }

    public boolean changePassword(String username,String password){

        Query query = Query.query(Criteria.where("username").is(username));
        Update update = Update.update("password",password);
        int result = mongoTemplate.updateFirst(query,update,"studentLoginTable").getN();

        if (result == 1){
            return true;
        }else {
            return false;
        }
    }





}
