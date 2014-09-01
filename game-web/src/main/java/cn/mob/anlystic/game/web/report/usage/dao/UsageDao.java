package cn.mob.anlystic.game.web.report.usage.dao;

import cn.mob.anlystic.game.web.common.CommonDao;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version 1.0 date: 2014/8/25
 * @author: Dempe
 */
@Component
public class UsageDao extends CommonDao {

    private final static String COL_NAME = "usage";

    public DBCursor find(DBObject query) {
        return find(COL_NAME,query);
    }


}
