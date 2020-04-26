package StudentManagementSystem.student.service;

import StudentManagementSystem.student.dao.StudentDao;
import StudentManagementSystem.student.dto.StudentImage;
import StudentManagementSystem.student.dto.StudentPersonalInformation;
import StudentManagementSystem.student.dto.StudentScore;
import StudentManagementSystem.student.dto.StudentTeachingEval;
import StudentManagementSystem.vo.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author hctrl
 * @date 2020/4/20 23:45
 */
@Service
public class StudentService {

    @Resource
    private StudentDao studentDao;


    public StudentPersonalInformation loadInfo(String loginname){

        StudentPersonalInformation studentPersonalInformation = studentDao.loadinfo(loginname);
        return studentPersonalInformation;
    }

    public boolean changeUsernameInfo(StudentPersonalInformation studentPersonalInformation){

        return studentDao.changeUsernameInfo(studentPersonalInformation);
    }

    public boolean uploadImage(StudentImage studentImage){

        return studentDao.uploadImage(studentImage);

    }

    public String loadImage(String studentID){

        return studentDao.loadImage(studentID);

    }

    public List<StudentScore> getStudentScore(StudentScore studentScore){

        return studentDao.getStudentScore(studentScore);
    }

    public boolean saveTeachingEval(List<StudentTeachingEval> studentTeachingEvals){

        return studentDao.saveTeachingEval(studentTeachingEvals);
    }


    public List<StudentTeachingEval> findTeachingEval(String name){

        return studentDao.findTeachingEval(name);

    }

    public Page<StudentPersonalInformation> getMailList(){

        Page<StudentPersonalInformation> page = new Page<>();

        List<StudentPersonalInformation> mailList = studentDao.getMailList();

        if (mailList.size() != 0 && mailList != null){
            page.setTotalCount(mailList.size());

            int totalPage = page.getTotalCount() / page.getPageSize();
            page.setTotalPage(page.getTotalCount() % page.getPageSize() == 0 ? totalPage : totalPage +1);
            page.setData(mailList);
        }


        return page;

    }

    public Page<StudentPersonalInformation> getMailListPage(int currentPage){

        Page<StudentPersonalInformation> page = new Page<>();
        page.setCurrentPage(currentPage);

        List<StudentPersonalInformation> mailListPage = studentDao.getMailListPage(page);
        page.setTotalCount(mailListPage.size());
        page.setTotalPage(mailListPage.size() % page.getPageSize() == 0 ? mailListPage.size() / page.getPageSize() : mailListPage.size() % page.getPageSize() + 1);

        page.setData(mailListPage);

        return page;
    }

}
