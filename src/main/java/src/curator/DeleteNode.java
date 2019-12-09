package src.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

public class DeleteNode {

    public boolean delete (CuratorFramework curatorFramework, String path) {
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            if (stat != null) {
                System.out.println("deleting!");
                curatorFramework.delete()
                .guaranteed()
                .deletingChildrenIfNeeded()
                .forPath(path);
                System.out.println("deleted!");
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