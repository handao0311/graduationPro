package StudentManagementSystem.teacher.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hctrl
 * @date 2020/5/1 18:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UploadStudentScore implements Serializable {

    public String teacherName;

    public String studentID;

    public Integer score;

    public Double credit;

}
