package com.yeb.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeb.server.common.RespBean;
import com.yeb.server.mapper.MenuRoleMapper;
import com.yeb.server.pojo.MenuRole;
import com.yeb.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eddie
 * @since 2022-01-29
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if (null == mids || 0 == mids.length){
            return RespBean.success("更新成功！");
        }
        Integer record = menuRoleMapper.insertRecord(rid, mids);
        if (record == mids.length){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
