package edu.cczu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.cczu.dto.AddRecordDto;
import edu.cczu.entity.Record;
import edu.cczu.utils.Result;


/**
 * (Record)服务接口
 *
 * @author 大只
 * @since 2022-11-09 14:14:32
 */
public interface RecordService extends IService<Record> {


    Result selectRecordList();

    Result addRecord(AddRecordDto addRecordDto);

    Result geRecordByUser();

    Result recordList(Integer pageNum, Integer pageSize, String keywords,String status);
}
