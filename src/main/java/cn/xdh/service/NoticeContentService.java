package cn.xdh.service;

import cn.xdh.entity.NoticeContent;

import javax.servlet.http.HttpServletRequest;

/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.service.impl
 * @created 2019-12-30 10:35
 * @function ""
 */
public interface NoticeContentService {
    //增加公告
    public Integer addNoticeContent(String content, Long time, HttpServletRequest request);

}
