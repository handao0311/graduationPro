package StudentManagementSystem.teacher.dao;

import StudentManagementSystem.login.dto.TeacherLogin;
import StudentManagementSystem.student.dto.StudentPersonalInformation;
import StudentManagementSystem.student.dto.StudentScore;
import StudentManagementSystem.teacher.dto.TeacherInfoTable;
import StudentManagementSystem.teacher.meta.UploadStudentScore;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hctrl
 * @date 2020/5/1 16:22
 */
@Repository
public class TeacherDao {

    @Resource
    private MongoTemplate mongoTemplate;

    public List<StudentPersonalInformation> getStudentInfo(){

        List<StudentPersonalInformation> studentPersonalInformationTable = mongoTemplate.findAll(StudentPersonalInformation.class, "studentPersonalInformationTable");

        return studentPersonalInformationTable;
    }

    public boolean uploadScore(List<UploadStudentScore> uploadStudentScores){

        Query query0 = Query.query(Criteria.where("name").is(uploadStudentScores.get(0).getTeacherName()));

        //查到课程id
        TeacherInfoTable teacherInfoTable = mongoTemplate.findOne(query0, TeacherInfoTable.class);
        System.out.println("teacherLogin" + teacherInfoTable.getCourseID());

        for (UploadStudentScore uploadStudentScore : uploadStudentScores){

            System.out.println(uploadStudentScore.getStudentID());
            Query query = Query.query(Criteria.where("studentID").is(uploadStudentScore.getStudentID()));

            //查到姓名
            StudentPersonalInformation studentPersonalInformationTable = mongoTemplate.findOne(query, StudentPersonalInformation.class, "studentPersonalInformationTable");
            System.out.println("name" + studentPersonalInformationTable.getLoginname());

            Query query1 = Query.query(Criteria.where("name").is(studentPersonalInformationTable.getLoginname()).and("courseID").is(teacherInfoTable.getCourseID()));
            Update update = Update.update("score", uploadStudentScore.getScore())
                    .set("credit", uploadStudentScore.getCredit());
            int res = mongoTemplate.updateFirst(query1, update, StudentScore.class).getN();
            System.out.println(res);
        }

        return true;

    }

}
