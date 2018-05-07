package com.microwise.halley.service.impl;

import com.google.common.base.Strings;
import com.microwise.halley.bean.po.PathPO;
import com.microwise.halley.bean.vo.ExhibitionStateVO;
import com.microwise.halley.dao.ExhibitionStateDao;
import com.microwise.halley.service.ExhibitionStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 外展状态 Service 实现
 *
 * @author li.jianfei
 * @date 2013-11-12
 */
@Service
@Scope("prototype")
@Transactional
public class ExhibitionStateServiceImpl implements ExhibitionStateService {

    @Autowired
    private ExhibitionStateDao exhibitionStateDao;

    @Override
    public ExhibitionStateVO findCurrentState(int exhibitionId) {
        return exhibitionStateDao.findCurrentState(exhibitionId);
    }

    @Override
    public List<ExhibitionStateVO> getHistoryState(int exhibitionId) {

        // 获取历史状态
        List<ExhibitionStateVO> exhibitionStateVOs = exhibitionStateDao.getHistoryState(exhibitionId);
        // 获取预设线路
        List<PathPO> pathPOList = exhibitionStateDao.getALLPathPO(exhibitionId);

        // 匹配历史状态目的地、操作人
        List<PathPO> pathPOListStart = new ArrayList<PathPO>();
        // 匹配历史状态目的地、操作人
        List<PathPO> pathPOListEnd = new ArrayList<PathPO>();
        for (int i = 1; i < pathPOList.size(); i++) {
            pathPOListStart.add(pathPOList.get(i - 1));
            pathPOListEnd.add(pathPOList.get(i));
        }

        for (int i = 0; i < exhibitionStateVOs.size(); i++) {
            ExhibitionStateVO exhibitionStateVO = exhibitionStateVOs.get(i);
            if (i < pathPOListEnd.size()) {
                exhibitionStateVO.setEndPathPO(pathPOListEnd.get(i));
                exhibitionStateVO.setStartPathPO(pathPOListStart.get(i));
            }
        }
        return exhibitionStateVOs;
    }

    @Override
    public PathPO getStartDestination(int exhibitionId) {
        return exhibitionStateDao.getStartDestination(exhibitionId);
    }

    @Override
    public PathPO getNearBy(int exhibitionId, double longitude, double latitude) {
        return exhibitionStateDao.getNearBy(exhibitionId, longitude, latitude);
    }

    /**
     * 自定义比较器1
     * 比较PathPO的id大小
     */
    class PathPOComparatorAsc implements Comparator<PathPO> {

        @Override
        public int compare(PathPO p1, PathPO p2) {
            int id1 = p1.getId();
            int id2 = p2.getId();
            return (id1 - id2);
        }

    }

    /**
     * 自定义比较器2
     * 比较PathPO的id大小
     */
    class PathPOComparatorDesc implements Comparator<PathPO> {

        @Override
        public int compare(PathPO p1, PathPO p2) {
            int id1 = p1.getId();
            int id2 = p2.getId();
            return (id2 - id1);
        }

    }
}
