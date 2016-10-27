/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyRecordDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 通知通告Service
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OaNotifyService extends CrudService<OaNotifyDao, OaNotify> {

    @Autowired
    private OaNotifyRecordDao oaNotifyRecordDao;
    @Autowired
    private OaNotifyDao oaNotifyDao;

    public OaNotify get(String id) {
        OaNotify entity = dao.get(id);
        return entity;
    }

    /**
     * 获取通知发送记录
     *
     * @param oaNotify
     * @return
     */
    public OaNotify getRecordList(OaNotify oaNotify) {
        oaNotify.setOaNotifyRecordList(oaNotifyRecordDao.findList(new OaNotifyRecord(oaNotify)));
        return oaNotify;
    }

    public Page<OaNotify> find(Page<OaNotify> page, OaNotify oaNotify) {
        oaNotify.setPage(page);
        page.setList(dao.findList(oaNotify));
        return page;
    }

    /**
     * 获取通知数目
     *
     * @param oaNotify
     * @return
     */
    public Long findCount(OaNotify oaNotify) {
        return dao.findCount(oaNotify);
    }

    @Transactional(readOnly = false)
    public void save(OaNotify oaNotify) {
        super.save(oaNotify);

        // 更新发送接受人记录
        oaNotifyRecordDao.deleteByOaNotifyId(oaNotify.getId());
        if (oaNotify.getOaNotifyRecordList().size() > 0) {
            for (OaNotifyRecord entity : oaNotify.getOaNotifyRecordList()) {
                oaNotifyRecordDao.insert(entity);
            }

        }
    }

    /**
     * 更新阅读状态
     */
    @Transactional(readOnly = false)
    public boolean updateReadFlag(OaNotify oaNotify) {
        boolean f = false;

        OaNotifyRecord oaNotifyRecord = new OaNotifyRecord(oaNotify);
        oaNotifyRecord.setUser(oaNotifyRecord.getCurrentUser());
        OaNotifyRecord _oaNotifyRecord = oaNotifyRecordDao.getByNotifyId(oaNotify.getId());


        if(_oaNotifyRecord!=null){
            if(_oaNotifyRecord.getReadFlag()!=null&&_oaNotifyRecord.getReadFlag().equals("0"))f = true;
        }
        oaNotifyRecord.setReadDate(new Date());
        oaNotifyRecord.setReadFlag("1");
        oaNotifyRecordDao.update(oaNotifyRecord);
        //刷新缓存menu
        UserUtils.refreshMenuList();
        return f;
    }
}