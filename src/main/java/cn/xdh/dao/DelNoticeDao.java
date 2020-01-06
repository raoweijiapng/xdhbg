package cn.xdh.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.dao
 * @created 2019-12-30 14:30
 * @function ""
 */
@Repository
public interface DelNoticeDao {
    public Integer delNoticeDao(@Param("id") Integer id);
}
