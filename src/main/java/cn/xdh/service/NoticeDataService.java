package cn.xdh.service;

import cn.xdh.entity.NoticeData;

import java.util.List;

/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.service
 * @created 2019-12-27 15:48
 * @function ""
 */
public interface NoticeDataService {
    public List<NoticeData> selectNoticeData();
    public List<NoticeData> searchNoticeData(String part);
}
