package edu.cczu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.cczu.dto.WxUserLoginDto;
import edu.cczu.entity.WxUser;
import edu.cczu.utils.Result;


/**
 * (WxUser)服务接口
 *
 * @author 大只
 * @since 2022-11-12 16:27:16
 */
public interface WxUserService extends IService<WxUser> {

    Result wxUserLogin(WxUserLoginDto wxUserLoginDto);

    Result userList(Integer pageNum, Integer pageSize, String keywords);
}
