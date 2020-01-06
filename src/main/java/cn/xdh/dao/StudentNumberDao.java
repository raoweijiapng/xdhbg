package cn.xdh.dao;

import cn.xdh.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.dao
 * @created 2019-12-27 9:00
 * @function ""
 */
@Repository
public interface StudentNumberDao {
    //人数
    public int selectTotalNumber();
    public int selectGraduNumber();
    public int selectNotGraduNumber();

    //阶段
    public int selectStageOne();
    public int selectStageTwo();
    public int selectStageThree();
    public int selectStageFour();
    public int selectStageFive();
}
