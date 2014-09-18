package cn.mob.anlystic.cluster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 分布式节点管理类
 * 当新添加节点或者remove节点通知HashedUtil节点变化，key和node的映射关系
 *
 * @author : Dempe
 * @version 1.0 date : 2014/9/17
 */
public class NodeMananger {

    private static Map<Node, NodeState> nodes = new ConcurrentHashMap<Node, NodeState>();

    /**
     * 获取活跃的节点
     *
     * @return
     */
    public static List getAliveNodes() {
        List<Node> aliveNodes = new ArrayList<Node>();
        Iterator<Map.Entry<Node, NodeState>> nodeEntry = nodes.entrySet().iterator();
        while (nodeEntry.hasNext()) {
            Map.Entry<Node, NodeState> entry = nodeEntry.next();
            if (entry.getValue().isAlive()) {
                aliveNodes.add(entry.getKey());
            }
        }
        return aliveNodes;

    }

    /**
     * 默认新添加的node为alive状态
     *
     * @param node
     */
    public static void put(Node node) {
        put(node, new NodeState("alive"));
    }

    public static void put(Node node, NodeState state) {
        if (!node.equals(nodes.get(node))) {
            nodes.put(node, state);
            notifyNode();
        }
    }

    public static void remove(Node node) {
        nodes.remove(node);
        notifyNode();
    }

    public static void notifyNode() {
        HashedUtil.reload();
    }
}
