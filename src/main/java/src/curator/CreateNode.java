package src.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CreateNode {
    public boolean create (CuratorFramework curatorFramework, String path, String content){
        
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            if (stat == null) {
                System.out.println("creating!");
                curatorFramework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(path, content.getBytes());
                System.out.println("creatd!");
                return true;
            } else {
                return false;
            }            
        }catch(Exception e){
            e.printStackTrace();
        } 

        return false;
    }
}