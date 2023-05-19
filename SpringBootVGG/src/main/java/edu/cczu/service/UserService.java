package edu.cczu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.cczu.dto.AddUserDto;
import edu.cczu.dto.UpdateUserDto;
import edu.cczu.entity.User;
import edu.cczu.utils.Result;


/**
 * 用户表(User)服务接口
 *
 * @author 大只
 * @since 2022-11-06 18:45:38
 */
public interface UserService extends IService<User> {

    Result getUserMenus();

    Result userList(Integer pageNum, Integer pageSize, String keywords);

    Result addUser(AddUserDto addUserDto);

    Result geUserById(Long id);

    Result updateUser(UpdateUserDto updateUserDto);


}
