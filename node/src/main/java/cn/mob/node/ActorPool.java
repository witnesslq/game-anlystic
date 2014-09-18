package cn.mob.node;

import akka.actor.ActorSelection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/16
 */
public class ActorPool {

    private static Map<String, ActorSelection> pool = new HashMap<String, ActorSelection>();

    public static void put(String path, ActorSelection actor) {
        pool.put(path, actor);
    }

    public static ActorSelection getActor(String path) {
        return pool.get(path);
    }

    public static int size() {
        return pool.size();
    }
}
