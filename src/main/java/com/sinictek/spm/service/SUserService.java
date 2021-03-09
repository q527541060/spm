package com.sinictek.spm.service;

import com.sinictek.spm.dao.SUserMapper;
import com.sinictek.spm.model.SUser;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
public interface SUserService extends IService<SUser> {
    public List<SUser> getTestAllUser(Integer lUserSize);
    public int addTestAllUser(SUser sUser);

}
