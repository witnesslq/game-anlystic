package cn.mob.anlystic.cluster;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/17
 */
public class NodeState {
    private String state;

    public NodeState(String state) {
        this.state = state;
    }

    public boolean isAlive() {
        return "alive".equals(state);
    }
}
