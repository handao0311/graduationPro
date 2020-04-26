package StudentManagementSystem.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hctrl
 * @date 2020/4/20 22:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "studentPersonalInformationTable")
public class StudentPersonalInformation implements Serializable {

    private String loginname;

    private String name;

    private String nikeName;

    private String sex;

    private String studentID;

    @Field("class")
    private String grade;

    @Field("QQ")
    private String qq;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dateOfBirth;

    private Integer admissionTime;

    private String motto;

}
