package StudentManagementSystem.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author hctrl
 * @date 2020/5/1 19:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "teacherInfoTable")
public class TeacherInfoTable implements Serializable {

    public String name;

    public String courseID;

}
