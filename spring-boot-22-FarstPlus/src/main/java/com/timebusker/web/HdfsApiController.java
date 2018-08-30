package com.timebusker.web;

import com.timebusker.common.R;
import com.timebusker.entity.OssEntity;
import com.timebusker.utils.PageUtils;
import com.timebusker.utils.Query;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @DESC:HdfsApiController
 * @author:timebusker
 * @date:2018/8/30
 */
@RestController
@RequestMapping("/hdfs")
public class HdfsApiController extends AbstractController {

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<OssEntity> ossList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            OssEntity oss = new OssEntity();
            oss.setId(i + "_" + System.currentTimeMillis());
            oss.setUrl("http://www.timebusker.top/master/img/top-photo/" + i + ".jpg");
            oss.setCreateDate(new Date());
        }
        int total = 1;
        PageUtils pageUtil = new PageUtils(ossList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }
}
