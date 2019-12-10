package src.curator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;

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

        /*
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
        */

        /*
        // 回调函数，实现异步
        String path = "/leo/l3";
        String content = "niubai!";
        ExecutorService pool = Executors.newCachedThreadPool();
        curatorFramework.create()
        .creatingParentsIfNeeded()
        .withMode(CreateMode.PERSISTENT)
        .inBackground(new BackgroundCallback(){
        
            @Override
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                // TODO Auto-generated method stub
                System.out.println("code: " + event.getResultCode());
                System.out.println("type: " + event.getType());
                System.out.println("thread: " + Thread.currentThread().getName());
                
            }
        })
        .forPath(path, content.getBytes());
        Thread.sleep(3000);
        */
        
        /*
        // query子节点
        GetChildrenNodes getChildrenNodes = new GetChildrenNodes();
        String path = "/leo";
        
        List<String> strs = getChildrenNodes.getChildren(curatorFramework, path);
        if (strs != null) {
            System.out.println("query success!");
            for (String str : strs) {
                System.out.println("query result:" + str);
            }
        } else {
            System.out.println("query fail!");
        }
        */

        // 监听
        /*
        使用NodeCache的方式去客服端实例中注册一个监听缓存，然后实现对应的监听方法即可，这里我们主要有两种监听方式。
        NodeCacheListener： 监听节点的新增、修改操作
        PathChildrenCacheListener：监听子节点的新增、修改、删除操作
        */
        /*
        String path = "/leo/l1";
        NodeCache nodeCache = new NodeCache(curatorFramework, path);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            */
            /**
             * <B>方法名称：</B>nodeChanged<BR>
             * <B>概要说明：</B>触发事件为创建节点和更新节点，在删除节点的时候并不触发此操作。<BR>
             * @see org.apache.curator.framework.recipes.cache.NodeCacheListener#nodeChanged()
             */
            /*
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("路径为：" + nodeCache.getCurrentData().getPath());
                System.out.println("数据为：" + new String(nodeCache.getCurrentData().getData()));
                System.out.println("状态为：" + nodeCache.getCurrentData().getStat());
                System.out.println("---------------------------------------");
            }
        }); 
        */

        /*
        String pPath = "/leo";
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, pPath, true);
        pathChildrenCache.start(StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            */
            /**
             * <B>方法名称：</B>监听子节点变更，只能是直系子节点不可以是孙子辈或者更小<BR>
             * <B>概要说明：</B>新建、修改、删除<BR>
             * @see org.apache.curator.framework.recipes.cache.PathChildrenCacheListener#childEvent(org.apache.curator.framework.CuratorFramework, org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent)
             */
            /*
            @Override
            public void childEvent(CuratorFramework cf, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                case CHILD_ADDED:
                    System.out.println("CHILD_ADDED :" + event.getData().getPath());
                    break;
                case CHILD_UPDATED:
                    System.out.println("CHILD_UPDATED :" + event.getData().getPath());
                    break;
                case CHILD_REMOVED:
                    System.out.println("CHILD_REMOVED :" + event.getData().getPath());
                    break;
                default:
                    break;
                }
            }
        });
        */
        Mutex mutex = new Mutex();
        mutex.testMutex(curatorFramework, "/leo");

        Thread.sleep(3000);

    }
}