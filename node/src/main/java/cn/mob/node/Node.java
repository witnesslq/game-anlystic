package cn.mob.node;

import java.io.Serializable;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/17
 */
public class Node implements Serializable {

    private String nodename;

    private String hostname;

    private int port;

    public Node(String nodename, String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        this.nodename = nodename;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (port != node.port) return false;
        if (hostname != null ? !hostname.equals(node.hostname) : node.hostname != null) return false;
        if (nodename != null ? !nodename.equals(node.nodename) : node.nodename != null) return false;

        return true;
    }

    public String getNodename() {
        return nodename;
    }

    @Override
    public int hashCode() {
        int result = nodename != null ? nodename.hashCode() : 0;
        result = 31 * result + (hostname != null ? hostname.hashCode() : 0);
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return new StringBuffer("akka.tcp://").append(nodename).append("@").append(hostname).append(":").append(port).toString();
    }
}
