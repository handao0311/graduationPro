package StudentManagementSystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author hctrl
 * @date 2020/4/26 19:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> implements Serializable {

    private Integer currentPage;

    private Integer previousPage;

    private Integer nextPage;

    private Integer pageSize = 3;

    private Integer totalCount;

    private Integer totalPage;

    private List<T> data;
}
