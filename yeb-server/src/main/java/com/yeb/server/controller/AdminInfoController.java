package com.yeb.server.controller;

import com.yeb.server.common.RespBean;
import com.yeb.server.pojo.Admin;
import com.yeb.server.service.IAdminService;
import com.yeb.server.utils.FastDFSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author Eddie
 * @since 2022-01-25
 */
@RestController
public class AdminInfoController {


    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "更新当前用户信息")
    @PutMapping("/admin/info")
    public RespBean updateAdmin(@RequestBody Admin admin, Authentication authentication) {
        if (adminService.updateById(admin)) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    admin,
                    null,
                    authentication.getAuthorities()));
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(value = "更新用户密码")
    @PutMapping("/admin/pass")
    public RespBean updateAdminPassword(@RequestBody Map<String,Object> info){
        String oldPass = (String) info.get("oldPass");
        String newPass = (String) info.get("pass");
        String adminId = (String) info.get("adminId");
        return adminService.updateAdminPassword(adminId,oldPass,newPass);
    }


    @ApiOperation(value = "更新用户头像")
    @PostMapping("/admin/userface")
    public RespBean updateAdminUserFace(MultipartFile file,Integer id,Authentication authentication){
        String[] filePath = FastDFSUtils.uploadFile(file);
        String url = FastDFSUtils.getFileUrl() + filePath[0] + "/" + filePath[1];
        return adminService.updateAdminUserFace(url,id,authentication);
    }
}
