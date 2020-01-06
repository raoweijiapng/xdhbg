package cn.xdh.dao;

import cn.xdh.entity.NoticeData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.dao
 * @created 2019-12-27 15:50
 * @function ""
 */
@Repository
public interface NoticeDataDao {

    public List<NoticeData> selectNoticeData();

    public List<NoticeData> searchNoticeData(@Param("part") String part);
}
