package src.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import java.util.*;

public class GetChildrenNodes {

    public List<String> getChildren (CuratorFramework curatorFramework, String path) {
        List<String> list = new ArrayList<String>();
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            System.out.println("querying!");
            if (stat != null) {
                System.out.println("query start!");
                list = curatorFramework.getChildren().forPath(path);
            } else {
                list = null;
            }      
            System.out.println("query end!");      
        }catch(Exception e){
            e.printStackTrace();
        } 

        return list;
    } 

}