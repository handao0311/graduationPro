package StudentManagementSystem.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author hctrl
 * @date 2020/4/22 23:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "studentImage")
public class StudentImage implements Serializable {

    public String studentID;

    public String imgUrl;


}
