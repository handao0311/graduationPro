package StudentManagementSystem.web.controller;

import StudentManagementSystem.student.dto.StudentPersonalInformation;
import StudentManagementSystem.teacher.meta.UploadStudentScore;
import StudentManagementSystem.teacher.service.TeacherService;
import StudentManagementSystem.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.List;

/**
 * @author hctrl
 * @date 2020/5/1 16:16
 */
@RestController
@RequestMapping("/api/teacher/")
public class teacherController {

    @Resource
    private TeacherService teacherService;


    /**
     * 获取学生信息
     *
     * @return
     */
    @GetMapping("score")
    public ResultVo getStudentInfo(){

        ResultVo resultVo = new ResultVo();

        List<StudentPersonalInformation> studentInfo = teacherService.getStudentInfo();

        if (studentInfo != null && studentInfo.size() >0){
            resultVo.setStatus(1);
            resultVo.setData(studentInfo);
        }else {
            resultVo.setStatus(0);
            resultVo.setMessage("没有任何学生信息");
        }

        return resultVo;
    }

    /**
     *
     * 上传学生成绩
     * @param uploadStudentScores
     * @return
     */
    @PostMapping("score")
    public ResultVo uploadScore(@RequestBody List<UploadStudentScore> uploadStudentScores){

        ResultVo resultVo = new ResultVo();

        if (teacherService.uploadScore(uploadStudentScores)){
            resultVo.setStatus(1);

        }else {
            resultVo.setStatus(0);
            resultVo.setMessage("修改失败");
        }

        return resultVo;
    }

}
