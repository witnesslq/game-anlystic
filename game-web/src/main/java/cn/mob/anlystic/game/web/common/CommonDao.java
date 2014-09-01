package cn.mob.anlystic.game.web.common;

import cn.mob.anlystic.game.web.R;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @version 1.0 date: 2014/8/25
 * @author: Dempe
 */
@Component
public class CommonDao {

    @Resource
    private Mongo mongo;

    protected DB db;

    @PostConstruct
    public void init(){
        db = mongo.getDB(R.dbName);
    }

    public DBCursor find(String collectionName,DBObject query){
       return db.getCollection(collectionName).find(query);
    }


}
