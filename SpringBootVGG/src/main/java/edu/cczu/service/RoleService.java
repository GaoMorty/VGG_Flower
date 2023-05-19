package edu.cczu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.cczu.dto.AddRoleDto;
import edu.cczu.dto.UpdateRoleDto;
import edu.cczu.entity.Role;
import edu.cczu.utils.Result;


/**
 * 角色信息表(Role)服务接口
 *
 * @author 大只
 * @since 2022-11-06 18:45:38
 */
public interface RoleService extends IService<Role> {

    Result roleList(Integer pageNum, Integer pageSize, String keywords);

    Result addRole(AddRoleDto addRoleDto);

    Result updateRole(UpdateRoleDto updateRoleDto);
}
