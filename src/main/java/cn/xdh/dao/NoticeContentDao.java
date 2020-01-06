package cn.xdh.dao;

import cn.xdh.entity.NoticeContent;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.dao
 * @created 2019-12-30 10:31
 * @function ""
 */
@Repository
public interface NoticeContentDao {
    public Integer addNoticeContent(@Param("content") String content, @Param("time") Long time);
}
