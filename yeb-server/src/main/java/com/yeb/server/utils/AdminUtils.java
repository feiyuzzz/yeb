package com.yeb.server.utils;

import com.yeb.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 *
 * </p>
 *
 * @author Eddie
 * @since 2022-01-25
 */
public class AdminUtils {

    /**
     * 获取当前登陆操作员
     * @return
     */
    public static Admin getCurrentAdmin(){
        return (Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
