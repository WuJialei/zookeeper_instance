package src.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

public class QueryNode {
    public String query (CuratorFramework curatorFramework, String path) {
        String str = null;
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            System.out.println("querying!");
            if (stat != null) {
                System.out.println("query start!");
                str = new String(curatorFramework.getData().forPath(path));
            } else {
                str = null;
            }      
            System.out.println("query end!");      
        }catch(Exception e){
            e.printStackTrace();
        } 

        return str;
    } 
}