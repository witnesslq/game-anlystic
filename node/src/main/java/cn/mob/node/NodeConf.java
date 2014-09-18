package cn.mob.node;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/17
 */
public class NodeConf {


    private Node localNode;
    private List<Node> preNodes;

    // private List<Node> nextNodes;

    public NodeConf() {

        List<Object> preNodeStrs = Conf.getList(Constants.PRE_NODES);
        // List<Object> nextNodeStrs = Conf.getList(Constants.NEXT_NODES);
        preNodes = fromStringList(preNodeStrs);
        // nextNodes = fromStringList(nextNodeStrs);
        localNode = fromString(Conf.getString(Constants.LOCAL_NODE));
        if (localNode == null) {
            localNode = new Node("node", "127.0.0.1", 2252);
        }

    }

    public void setLocalNode(String nodeStr) {
        setLocalNode(fromString(nodeStr));
    }

    public void setPreNodes(String nodesStr) {
        List<Node> nodes = new ArrayList<Node>();
        String strs[] = nodesStr.split(",");
        for (String str : strs) {
            nodes.add(fromString(str));
        }
        setPreNodes(nodes);
    }

    public void setLocalNode(Node localNode) {
        this.localNode = localNode;
    }

    public void setPreNodes(List<Node> preNodes) {
        this.preNodes = preNodes;
    }

    public Node getLocalNode() {
        return localNode;
    }


    public List<Node> getPreNodes() {
        return preNodes;
    }


    public List<Node> fromStringList(List<Object> nodeStrs) {
        if (nodeStrs == null) {
            return null;
        }
        List<Node> nodes = new ArrayList<Node>();
        for (Object nodeStr : nodeStrs) {
            nodes.add(fromString((String) nodeStr));
        }
        return nodes;
    }

    public Node fromString(String nodeStr) {
        if (nodeStr == null) {
            return null;
        }
        String nodename = StringUtils.substringBefore(nodeStr, "@");
        String uri = StringUtils.substringAfter(nodeStr, "@");
        String hostname = StringUtils.substringBefore(uri, ":");
        int port = Integer.parseInt(StringUtils.substringAfter(uri, ":"));
        return new Node(nodename, hostname, port);
    }

    public String getLocalNodeName() {
        String localNode = Conf.getString(Constants.LOCAL_NODE);
        return StringUtils.substringBefore(localNode, "@");
    }


}
