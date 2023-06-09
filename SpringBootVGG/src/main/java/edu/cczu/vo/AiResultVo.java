package edu.cczu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 大只
 * @date 2022-11-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiResultVo<T> {

    private Integer status;

    private String msg;

    private T data;
}
