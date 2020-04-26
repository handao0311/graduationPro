package StudentManagementSystem.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author hctrl
 * @date 2020/4/24 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "studentTeachingEvaluationTable")
public class StudentTeachingEval implements Serializable {

    private String studentName;

    private String courseName;

    private String teacherName;

    private String quality;

    private String interaction;

    private String novel;

}
