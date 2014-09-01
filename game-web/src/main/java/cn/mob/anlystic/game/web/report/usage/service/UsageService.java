package cn.mob.anlystic.game.web.report.usage.service;

import cn.mob.anlystic.game.web.report.usage.dao.UsageDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version 1.0 date: 2014/8/25
 * @author: Dempe
 */
@Component
public class UsageService {

    @Resource
    private UsageDao usageDao;

    public DBCursor getPeriodList(String startDate,String endDate){
        DBObject query = new BasicDBObject();
        query.put("","");
        return usageDao.find(query);
    }

}

