package cn.xdh.service;


/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.service
 * @created 2019-12-27 9:59
 * @function ""
 */
public interface StudentNumberService {
    public int selectTotalNumber();
    public int selectGraduNumber();
    public int selectNotGraduNumber();

    public int selectStageOne();
    public int selectStageTwo();
    public int selectStageThree();
    public int selectStageFour();
    public int selectStageFive();
}
