package StudentManagementSystem.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author hctrl
 * @date 2020/4/24 1:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "studentScoreTable")
public class StudentScore implements Serializable {

    public String name;

    public Integer year;

    public String team;

    public String courseName;

    public String courseID;

    public String type;

    public Double credit;

    public Integer score;

}
