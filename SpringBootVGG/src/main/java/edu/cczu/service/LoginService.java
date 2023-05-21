package edu.cczu.service;

import edu.cczu.dto.UserLoginDto;
import edu.cczu.utils.Result;

/**
 * @author 大只
 * @date 2022-11-06
 */
public interface LoginService {
    Result login(UserLoginDto userLoginDto);
}
