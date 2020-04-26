package StudentManagementSystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hctrl
 * @date 2020/4/16 18:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo implements Serializable {

    private int status;

    private String message;

    private Object data;

}
