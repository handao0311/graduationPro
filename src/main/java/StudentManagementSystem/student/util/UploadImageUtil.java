package StudentManagementSystem.student.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author hctrl
 * @date 2020/4/22 12:20
 */
public class UploadImageUtil {

    public String load_image(MultipartFile file, HttpServletRequest request) throws Exception {

        if (file != null) {

            System.out.println("图片不为空");
            String fileName = file.getOriginalFilename();
            String type = fileName.substring(fileName.indexOf(".") + 1);
            System.out.println(fileName + "   " + type);

            if (type != null) {

                if ("JPG".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPEG".equals(type.toUpperCase())) {

                    String path = request.getSession().getServletContext().getRealPath("/static/image/");
                    System.out.println("path   " + path);

//                    String tureFileName = request.getParameter("studentID") + "." + type;
//                    System.out.println(tureFileName);

//                    path = path + tureFileName;
                    path = path + fileName;

//                  转换图片到指定路径
                    file.transferTo(new File(path));

                    return fileName;
                }else {
                    System.out.println("文件格式不正确");
                    return null;
                }


            }else {
                System.out.println("文件格式为空");
                return null;
            }


        }else {
            System.out.println("传入的文件为空");
            return null;
        }

    }


}
