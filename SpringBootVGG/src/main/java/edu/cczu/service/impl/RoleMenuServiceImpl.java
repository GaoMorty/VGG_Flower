package edu.cczu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.cczu.entity.RoleMenu;
import edu.cczu.mapper.RoleMenuMapper;
import edu.cczu.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(RoleMenu)服务实现类
 *
 * @author 大只
 * @since 2022-11-06 18:45:38
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
