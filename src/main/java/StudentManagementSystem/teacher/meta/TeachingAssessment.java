package StudentManagementSystem.teacher.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hctrl
 * @date 2020/5/12 2:29
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeachingAssessment {

    private Integer qualityYx;
    private Integer qualityLh;
    private Integer qualityYb;

    private Integer interactionYx;
    private Integer interactionLh;
    private Integer interactionYb;

    private Integer NovelYx;
    private Integer NovelLh;
    private Integer NovelYb;

}
