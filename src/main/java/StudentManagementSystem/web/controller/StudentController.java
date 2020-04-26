package StudentManagementSystem.web.controller;

import StudentManagementSystem.student.dto.StudentImage;
import StudentManagementSystem.student.dto.StudentPersonalInformation;
import StudentManagementSystem.student.dto.StudentScore;
import StudentManagementSystem.student.dto.StudentTeachingEval;
import StudentManagementSystem.student.service.StudentService;
import StudentManagementSystem.student.util.PDFUtil;
import StudentManagementSystem.student.util.UploadImageUtil;
import StudentManagementSystem.vo.Page;
import StudentManagementSystem.vo.ResultVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @author hctrl
 * @date 2020/4/20 22:36
 */
@RestController
@RequestMapping("/api/")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 加载学生个人信息
     *
     * @param loginname
     * @return
     */
    @GetMapping("personalInformation")
    public ResultVo loadUsernameInfo(@RequestParam(value = "loginname") String loginname){

        StudentPersonalInformation studentPersonalInformation = studentService.loadInfo(loginname);
        //System.out.println("=================" + studentPersonalInformation);

        ResultVo resultVo = new ResultVo();
        if (studentPersonalInformation != null){

            resultVo.setStatus(1);
            resultVo.setData(studentPersonalInformation);
        }else {
            resultVo.setStatus(0);
            resultVo.setMessage("没有该用户");
        }

        return resultVo;
    }

    /**
     * 改变学生信息
     *
     * @param studentPersonalInformation
     * @return
     */
    @PostMapping("personalInformation")
    public ResultVo changeUsernameInfo(@RequestBody StudentPersonalInformation studentPersonalInformation){

        ResultVo resultVo = new ResultVo();
        boolean bool = studentService.changeUsernameInfo(studentPersonalInformation);

        if (bool){
            resultVo.setStatus(1);
        }else {
            resultVo.setStatus(0);
            resultVo.setMessage("修改失败");
        }
        return resultVo;
    }

    /**
     * 上传图片
     *
     * @param files
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("image")
    public ResultVo uploadImage(@RequestParam("files") MultipartFile files, HttpServletRequest request) throws Exception{

        ResultVo resultVo = new ResultVo();

        UploadImageUtil uploadImageUtil = new UploadImageUtil();
        String fileName = uploadImageUtil.load_image(files,request);

        if (fileName != null){

            String studentID = request.getParameter("studentID");

            StudentImage studentImage = new StudentImage();
            studentImage.setStudentID(studentID);
            studentImage.setImgUrl(fileName);

            if (studentService.uploadImage(studentImage)){
                resultVo.setStatus(1);
                resultVo.setData(studentImage);
            }else {
                resultVo.setStatus(0);
                resultVo.setMessage("上传图片失败");
            }
        }


        return resultVo;

    }

    /**
     * 加载图片
     *
     * @param studentID
     * @param request
     * @return
     */
    @GetMapping("image")
    public ResultVo loadImage(@RequestParam(value = "studentID") String studentID,HttpServletRequest request){

        ResultVo resultVo = new ResultVo();

        String imgUrl = studentService.loadImage(studentID);

        if (imgUrl != null){

            String path = request.getSession().getServletContext().getRealPath("/static/image/");

            String fileName = path + imgUrl;

            File file = new File(fileName);

            if (file.exists()){
                resultVo.setData(imgUrl);
            }else {
                resultVo.setData("default.jpg");
            }

        }else {
            resultVo.setData("default.jpg");
        }

        return resultVo;
    }


    /**
     * 获取成绩
     * @param name
     * @param year
     * @param team
     * @return
     */
    @GetMapping("score")
    public ResultVo getStudentScore(@RequestParam(value = "name") String name,@RequestParam(value = "year") String year,@RequestParam(value = "team") String team){


        ResultVo resultVo = new ResultVo();
        StudentScore studentScore = new StudentScore();

        studentScore.setName(name);
        studentScore.setYear(Integer.valueOf(year));
        studentScore.setTeam(team);
        List<StudentScore> list = studentService.getStudentScore(studentScore);


        if (list != null && list.size() != 0){

            resultVo.setData(list);
            resultVo.setStatus(1);

        }else {
            resultVo.setStatus(0);
            resultVo.setMessage("没有查到成绩");
        }
        return resultVo;
    }

    /**
     * 创建教学评估
     * @param studentTeachingEvals
     * @return
     */
    @PostMapping("teachingEval")
    public ResultVo saveTeachingEval(@RequestBody List<StudentTeachingEval> studentTeachingEvals){

        ResultVo resultVo = new ResultVo();

        if (studentService.saveTeachingEval(studentTeachingEvals)){

            resultVo.setStatus(1);
            resultVo.setData(studentTeachingEvals);
        }else {
            resultVo.setStatus(0);
            resultVo.setMessage("教学评估失败");
        }

        return resultVo;
    }

    /**
     * 查询教学评估
     *
     * @param name
     * @return
     */
    @GetMapping("teachingEval")
    public ResultVo findTeachingEval(@RequestParam(value = "name") String name){

        ResultVo resultVo = new ResultVo();

        List<StudentTeachingEval> teachingEval = studentService.findTeachingEval(name);

        if (teachingEval != null && teachingEval.size() != 0){

            resultVo.setStatus(1);
            resultVo.setData(teachingEval);
        }else {

            resultVo.setStatus(0);
            resultVo.setMessage("还未进行教学评估");
        }

        return resultVo;
    }

    @PostMapping("pdf")
    public ResultVo download_PDF(@RequestBody List<StudentScore> studentScores,HttpServletRequest request){

//        for (StudentScore studentScore : studentScores){
//            System.out.println(studentScore);
//        }

        ResultVo resultVo = new ResultVo();

        StudentScore studentScore = studentScores.get(0);

        String path = request.getSession().getServletContext().getRealPath("/file/");
        File file = new File(path + studentScore.getName() + studentScore.getYear() + studentScore.getTeam() + ".pdf");
        try{

            PDFUtil studentScorePDFUtil = new PDFUtil(file);
            studentScorePDFUtil.generatePDF(studentScores);
        }catch (Exception e){
            e.printStackTrace();
        }

        resultVo.setStatus(1);
        resultVo.setData("http://localhost:8080/graduationPro/file/" + studentScore.getName() + studentScore.getYear() + studentScore.getTeam() + ".pdf");

        return resultVo;
    }


    @GetMapping("mailList")
    public ResultVo getMailList(){

        ResultVo resultVo = new ResultVo();

        Page<StudentPersonalInformation> mailList = studentService.getMailList();

        if (mailList != null){

            resultVo.setStatus(1);
            resultVo.setData(mailList);

        }else {

            resultVo.setStatus(0);
            resultVo.setMessage("没有任何数据");
        }

        return resultVo;
    }

    @GetMapping("mailListPage")
    public ResultVo getMailListPage(@RequestParam(value = "currentPage") String currentPage){

        ResultVo resultVo = new ResultVo();

        Page<StudentPersonalInformation> mailListPage = studentService.getMailListPage(Integer.valueOf(currentPage));

        if (mailListPage.getTotalCount() != 0){
            resultVo.setStatus(1);
            resultVo.setData(mailListPage);
        }else {

            resultVo.setStatus(0);
            resultVo.setMessage("没有找到任何数据");
        }

        return resultVo;
    }

}
