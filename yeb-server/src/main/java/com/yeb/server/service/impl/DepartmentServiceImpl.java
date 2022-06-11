package com.yeb.server.service.impl;

import com.yeb.server.common.RespBean;
import com.yeb.server.pojo.Department;
import com.yeb.server.mapper.DepartmentMapper;
import com.yeb.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eddie
 * @since 2022-01-27
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @Override
    public RespBean addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDep(department);
        if (1==department.getResult()){
            return RespBean.success("添加部门成功！",department);
        }
        return RespBean.error("添加部门失败！");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public RespBean deleteDepartment(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.deleteDep(dep);
        if (dep.getResult() == -2){
            return RespBean.error("该部门下还有子部门，删除失败！");
        }
        if (-1 == dep.getResult()){
            return RespBean.error("该部门下还有员工，删除失败！");
        }
        if (1 == dep.getResult()){
            return RespBean.success("删除成功！");
        }
        return RespBean.success("删除失败！");
    }
}
