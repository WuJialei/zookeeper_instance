package src.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

public class UpdateNode {
    public boolean update (CuratorFramework curatorFramework, String path, String content) {
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            if (stat != null) {
                System.out.println("更新时间为：" + System.currentTimeMillis());
                System.out.println("update start!");
                curatorFramework.setData()
                .forPath(path, content.getBytes());
                System.out.println("update end!");
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
