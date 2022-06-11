package com.yeb.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeb.server.common.RespBean;
import com.yeb.server.pojo.MenuRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eddie
 * @since 2022-01-29
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
