package cn.xdh.service.impl;

import cn.xdh.dao.NoticeDataDao;
import cn.xdh.entity.NoticeData;
import cn.xdh.service.NoticeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.service.impl
 * @created 2019-12-27 15:57
 * @function ""
 */
@Service
public class NoticeDataServiceImpl implements NoticeDataService {
    @Autowired
    private NoticeDataDao noticeDataDao;
    @Override
    public List<NoticeData> selectNoticeData() {
        List<NoticeData> noticeData = noticeDataDao.selectNoticeData();
        return noticeData;
    }
    @Override
    public List<NoticeData> searchNoticeData(String part) {
        List<NoticeData> noticeData = noticeDataDao.searchNoticeData(part);
        return noticeData;
    }
}
