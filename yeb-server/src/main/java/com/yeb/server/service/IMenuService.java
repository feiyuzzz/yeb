package com.yeb.server.service;

import com.yeb.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eddie
 * @since 2022-01-27
 */
public interface IMenuService extends IService<Menu> {



    /**
     * 根据用户id获取菜单
     * @return
     */
    List<Menu> getMenusByAdminId();


    /**
     * 根据角色权限获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询所有的菜单
     * @return
     */
    List<Menu> getAllMenus();
}
