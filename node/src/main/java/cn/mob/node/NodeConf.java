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

    }

    public Node getLocalNode() {
        return localNode;
    }


    public List<Node> getPreNodes() {
        return preNodes;
    }


    private List<Node> fromStringList(List<Object> nodeStrs) {
        List<Node> nodes = new ArrayList<Node>();
        for (Object nodeStr : nodeStrs) {
            nodes.add(fromString((String) nodeStr));
        }
        return nodes;
    }

    private Node fromString(String nodeStr) {
        String nodename = StringUtils.substringBefore(nodeStr,"@");
        String uri = StringUtils.substringAfter(nodeStr,"@");
        String hostname = StringUtils.substringBefore(uri, ":");
        int port = Integer.parseInt(StringUtils.substringAfter(uri, ":"));
        return new Node(nodename, hostname, port);
    }

    public String getLocalNodeName(){
        String localNode = Conf.getString(Constants.LOCAL_NODE);
        return StringUtils.substringBefore(localNode,"@");
    }


}
