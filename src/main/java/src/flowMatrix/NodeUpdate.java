package src.flowMatrix;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import src.curator.UpdateNode;

public class NodeUpdate {

    static final String CONNECT_ADDR = "10.10.27.126:2182,10.10.27.123:2182,10.10.27.115:2182";
    static final int SESSION_TIME = 5000;

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
            .connectString(CONNECT_ADDR)
            .sessionTimeoutMs(SESSION_TIME)
            .retryPolicy(retryPolicy)
            .namespace("flow-matrix")
            .build();

        curatorFramework.start();

        UpdateNode updateNode = new UpdateNode();
        updateNode.update(curatorFramework, "/test", "t8");
    }

}
