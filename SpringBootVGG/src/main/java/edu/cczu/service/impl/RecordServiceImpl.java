package edu.cczu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.cczu.dto.AddRecordDto;
import edu.cczu.entity.Record;
import edu.cczu.error.SystemException;
import edu.cczu.mapper.RecordMapper;
import edu.cczu.service.RecordService;
import edu.cczu.utils.AppHttpCodeEnum;
import edu.cczu.utils.BeanCopyUtils;
import edu.cczu.utils.Result;
import edu.cczu.utils.SysConstant;
import edu.cczu.vo.MyRecordVo;
import edu.cczu.vo.PageVo;
import edu.cczu.vo.RecordVo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * (Record)表服务实现类
 *
 * @author 大只
 * @since 2022-11-09 14:14:32
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Override
    public Result selectRecordList() {
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Record::getStatus, SysConstant.RECORD_STATUS_NORMAL);
        List<Record> list = list(queryWrapper);
        List<RecordVo> recordVos = BeanCopyUtils.copyBeanList(list, RecordVo.class);
        return Result.okResult(recordVos);
    }

    @Override
    public Result addRecord(AddRecordDto addRecordDto) {

        Record record = BeanCopyUtils.copyBean(addRecordDto, Record.class);

        //检查地理位置是否存在
        Record one = getOne(new QueryWrapper<Record>().lambda()
                .eq(Record::getLongitude, record.getLongitude())
                .eq(Record::getLatitude, record.getLatitude())
        );

        if (Objects.nonNull(one)) {
            throw new SystemException(AppHttpCodeEnum.LOCATION_REPETITION);
        }

        record.setCreateBy(StpUtil.getLoginIdAsString());
        record.setCreateTime(new Date());

        //插入记录
        boolean save = save(record);

        if (save) {
            return Result.okResult();
        }
        return Result.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public Result geRecordByUser() {
        String openId = StpUtil.getLoginIdAsString();

        List<Record> list = list(new QueryWrapper<Record>().lambda()
                .eq(Record::getCreateBy, openId)
        );

        List<MyRecordVo> myRecordVos = BeanCopyUtils.copyBeanList(list, MyRecordVo.class);

        return Result.okResult(myRecordVos);
    }

    @Override
    public Result recordList(Integer pageNum, Integer pageSize, String keywords, String status) {
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        //模糊查询
        queryWrapper.like(Objects.nonNull(keywords), Record::getName, keywords);
        queryWrapper.like(Objects.nonNull(status), Record::getStatus, status);

        //分页
        Page<Record> rolePage = new Page<>(pageNum, pageSize);
        page(rolePage, queryWrapper);
        PageVo pageVo = new PageVo(rolePage.getRecords(), rolePage.getTotal());
        return Result.okResult(pageVo);
    }


}
