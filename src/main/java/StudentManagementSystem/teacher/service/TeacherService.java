package StudentManagementSystem.teacher.service;

import StudentManagementSystem.student.dto.StudentPersonalInformation;
import StudentManagementSystem.teacher.dao.TeacherDao;
import StudentManagementSystem.teacher.meta.TeachingAssessment;
import StudentManagementSystem.teacher.meta.UploadStudentScore;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hctrl
 * @date 2020/5/1 16:20
 */
@Service
public class TeacherService {

    @Resource
    private TeacherDao teacherDao;

    public List<StudentPersonalInformation> getStudentInfo(){

        return teacherDao.getStudentInfo();

    }

    public boolean uploadScore(List<UploadStudentScore> uploadStudentScores){

        return teacherDao.uploadScore(uploadStudentScores);

    }

    public TeachingAssessment seeTeachingEval(String name){


        return teacherDao.seeTeachingEval(name);


    }





}
