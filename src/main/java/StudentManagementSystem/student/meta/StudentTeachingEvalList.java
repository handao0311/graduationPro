package StudentManagementSystem.student.meta;

import StudentManagementSystem.student.dto.StudentTeachingEval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author hctrl
 * @date 2020/4/24 15:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTeachingEvalList implements Serializable {

    private List<StudentTeachingEval> studentTeachingEvals;

}
