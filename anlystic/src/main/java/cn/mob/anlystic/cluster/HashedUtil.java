package cn.mob.anlystic.cluster;


import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/17
 */
public class HashedUtil {

    private static List<Node> nodes;
    private static ConsistentHash<Node> consistentHash;

    static {
        nodes = NodeMananger.getAliveNodes();
        consistentHash = new ConsistentHash<Node>(1000, nodes);
    }

    public static Node getNodeByKey(String key) {
        return consistentHash.get(key);
    }

    public static void reload(){
        nodes = NodeMananger.getAliveNodes();
        consistentHash = new ConsistentHash<Node>(1000,nodes);
    }


}
