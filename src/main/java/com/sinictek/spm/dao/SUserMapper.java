package com.sinictek.spm.dao;

import com.sinictek.spm.model.SUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
public interface SUserMapper extends BaseMapper<SUser> {

    public List<SUser> getTestAllUser();
}
