package cn.xdh.service.impl;

import cn.xdh.dao.StudentNumberDao;
import cn.xdh.service.StudentNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.service.impl
 * @created 2019-12-27 10:00
 * @function ""
 */
@Service
public class StudentNumberServiceImpl implements StudentNumberService {
    @Autowired
    private StudentNumberDao studentnumberdao;

    @Override
    public int selectTotalNumber() {

        return studentnumberdao.selectTotalNumber();
    }

    @Override
    public int selectGraduNumber() {

        return studentnumberdao.selectGraduNumber();
    }

    @Override
    public int selectNotGraduNumber() {

        return studentnumberdao.selectNotGraduNumber();
    }

    @Override
    public int selectStageOne() {
        return studentnumberdao.selectStageOne();
    }

    @Override
    public int selectStageTwo() {
        return studentnumberdao.selectStageTwo();
    }

    @Override
    public int selectStageThree() {
        return studentnumberdao.selectStageThree();
    }

    @Override
    public int selectStageFour() {
        return studentnumberdao.selectStageFour();
    }

    @Override
    public int selectStageFive() {
        return studentnumberdao.selectStageFive();
    }
}
