package cn.mob.anlystic.game.web.report.usage.controller;

import cn.mob.anlystic.game.web.report.usage.dao.UsageDao;
import cn.mob.anlystic.game.web.report.usage.service.UsageService;
import com.alibaba.fastjson.JSONArray;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @version 1.0 date: 2014/8/25
 * @author: Dempe
 */
@Controller
public class UsageController {

    @Resource
    private UsageService usageService;

    /**
     * 获取用户使用概述
     *
     * @return
     */
    public String get(String startDate, String endDate) {
        return null;
    }

    /**
     * @return
     */
    public String list(String startDate, String endDate) {
        DBCursor dbCursor = usageService.getPeriodList(startDate, endDate);
        List<DBObject> list =  dbCursor.toArray();
        return JSONArray.toJSON(list).toString();
    }

}
