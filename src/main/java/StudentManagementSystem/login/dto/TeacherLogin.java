package StudentManagementSystem.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author hctrl
 * @date 2020/4/17 1:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "teacherLoginTable")
public class TeacherLogin implements Serializable {

    @Field
    private String username;

    @Field
    private String password;

    private String role;
}
