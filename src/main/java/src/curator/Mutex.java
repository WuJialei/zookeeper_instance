package src.curator;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import java.util.*;
import java.lang.Thread;

public class Mutex {
    
    int num = 20;

    public void getNumber() {
        --num;
        System.out.println("num: " + num);
    }

    public void testMutex (CuratorFramework client, String path) {
        InterProcessMutex lock = new InterProcessMutex(client, path);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        int cnt = 20;
        for (int i = 0; i < cnt; ++i) {
            new Thread(
                new Runnable(){
                
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try{
                            countDownLatch.await();
                            lock.acquire();
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                            System.out.println(sdf.format(new Date()));
                            getNumber();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally{
                            try{
                                lock.release();
                            } catch(Exception e){
                                e.printStackTrace();
                            }
                        }                       

                    }
                }, "t"+i).start();
        }
        countDownLatch.countDown();
    }
}