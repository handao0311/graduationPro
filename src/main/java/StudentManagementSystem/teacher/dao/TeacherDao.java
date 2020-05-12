package StudentManagementSystem.teacher.dao;

import StudentManagementSystem.login.dto.TeacherLogin;
import StudentManagementSystem.student.dto.StudentPersonalInformation;
import StudentManagementSystem.student.dto.StudentScore;
import StudentManagementSystem.teacher.dto.TeacherInfoTable;
import StudentManagementSystem.teacher.meta.TeachingAssessment;
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


    public TeachingAssessment seeTeachingEval(String name){

        TeachingAssessment teachingAssessment =  new TeachingAssessment();

        Query query = Query.query(Criteria.where("name").is(name));

        TeacherInfoTable teacherInfoTable = mongoTemplate.findOne(query, TeacherInfoTable.class, "teacherInfoTable");
        System.out.println(teacherInfoTable.getCourseName());
        Query query1 = Query.query(Criteria.where("courseName").is(teacherInfoTable.getCourseName()).and("quality").is("优秀"));

        long qualityYx = mongoTemplate.count(query1, "studentTeachingEvaluationTable");

        teachingAssessment.setQualityYx((int) qualityYx);

        Query query2 = Query.query(Criteria.where("courseName").is(teacherInfoTable.getCourseName()).and("quality").is("良好"));

        long qualityLh = mongoTemplate.count(query2, "studentTeachingEvaluationTable");

        teachingAssessment.setQualityLh((int) qualityLh);

        Query query3 = Query.query(Criteria.where("courseName").is(teacherInfoTable.getCourseName()).and("quality").is("一般"));

        long qualityYb = mongoTemplate.count(query3, "studentTeachingEvaluationTable");

        teachingAssessment.setQualityYb((int) qualityYb);








        Query query4 = Query.query(Criteria.where("courseName").is(teacherInfoTable.getCourseName()).and("interaction").is("优秀"));

        long interactionYx = mongoTemplate.count(query4, "studentTeachingEvaluationTable");

        teachingAssessment.setInteractionYx((int) interactionYx);

        Query query5 = Query.query(Criteria.where("courseName").is(teacherInfoTable.getCourseName()).and("interaction").is("良好"));

        long interactionLh = mongoTemplate.count(query5, "studentTeachingEvaluationTable");

        teachingAssessment.setInteractionLh((int) interactionLh);

        Query query6 = Query.query(Criteria.where("courseName").is(teacherInfoTable.getCourseName()).and("interaction").is("一般"));

        long interactionYb = mongoTemplate.count(query6, "studentTeachingEvaluationTable");

        teachingAssessment.setInteractionYb((int) interactionYb);






        Query query7 = Query.query(Criteria.where("courseName").is(teacherInfoTable.getCourseName()).and("novel").is("优秀"));

        long novelYx = mongoTemplate.count(query7, "studentTeachingEvaluationTable");

        teachingAssessment.setNovelYx((int) novelYx);

        Query query8 = Query.query(Criteria.where("courseName").is(teacherInfoTable.getCourseName()).and("novel").is("良好"));

        long novelLh = mongoTemplate.count(query8, "studentTeachingEvaluationTable");

        teachingAssessment.setNovelLh((int) novelLh);

        Query query9 = Query.query(Criteria.where("courseName").is(teacherInfoTable.getCourseName()).and("novel").is("一般"));

        long novelYb = mongoTemplate.count(query9, "studentTeachingEvaluationTable");

        teachingAssessment.setNovelYb((int) novelYb);

        System.out.println(teachingAssessment);

        return teachingAssessment;
    }

}
