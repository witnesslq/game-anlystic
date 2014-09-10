package cn.mob.anlystic;

import cn.mob.anlystic.sample.Sample;
import cn.mob.anlystic.store.ReportDB;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/9
 */
public class TestScala {

    public static void main(String args[]){

        ReportDB.hincrBy("hello","scala");
        System.out.println(R.drun());


    }
}
