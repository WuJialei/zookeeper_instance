package src.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooKeeper.States;

public class CuratorBase {

    static final String CONNECT_ADDR = "10.10.27.35:2182,10.10.27.36:2182,10.10.27.37:2182";
    static final int SESSION_TIME = 5000;
    public static void main (String[] args) throws Exception{

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
        .connectString(CONNECT_ADDR)
        .sessionTimeoutMs(SESSION_TIME)
        .retryPolicy(retryPolicy)
        .namespace("leo")
        .build();

        curatorFramework.start();

        System.out.println(States.CONNECTED);
        System.out.println(curatorFramework.getState());

        /*
        // create节点
        CreateNode createNode = new CreateNode();
        String path = "/leo/l2";
        String content = "hello";
        
        boolean createResult = createNode.create(curatorFramework, path, content);
        if (createResult) {
            System.out.println("create success!");
        } else {
            System.out.println("create fail!");
        }
        */

        /*
        // delete节点
        DeleteNode deleteNode = new DeleteNode();
        String path = "/leo/l2";
        
        boolean deleteResult = deleteNode.delete(curatorFramework, path);
        if (deleteResult) {
            System.out.println("delete success!");
        } else {
            System.out.println("delete fail!");
        }
        */
        
        /*
        // query节点
        QueryNode queryNode = new QueryNode();
        String path = "/leo/l1";
        
        String queryResult = queryNode.query(curatorFramework, path);
        if (queryResult != null) {
            System.out.println("query success!");
            System.out.println("query result:" + queryResult);
        } else {
            System.out.println("query fail!");
        }
        */

        // delete节点
        UpdateNode updateNode = new UpdateNode();
        String path = "/leo/l1";
        String content = "world";
        
        boolean updateResult = updateNode.update(curatorFramework, path, content);
        if (updateResult) {
            System.out.println("update success!");
        } else {
            System.out.println("update fail!");
        }
        
        
    }
}