package concurrency;

import com.sun.istack.internal.Pool;

import java.util.concurrent.*;

public class CreateThread {
    public static void main(String args[]){
        ThreadUtils utils = new ThreadUtils();
        utils.daemonThread();
    }

}
class ThreadUtils{
    /**
     * 创建Thread的4种方法
     */
    public void createThread(){
        //1.继承Thread
        ExtendThread th0 = new ExtendThread();
        th0.start();
        //2.实现Runnable接口
        ImplThread th1 = new ImplThread();
        new Thread(th1).start();
        //3.创建匿名类
        Thread th2 = new Thread(){
            @Override
            public void run(){
                System.out.println("创建匿名类");
            }
        };
        th2.start();
        //4.实现Callable接口
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new CallThread());
        try {
            System.out.println(future.get()+"1");
            System.out.println(future.get()+"2");
            System.out.println(future.get()+"3");
            System.out.println(future.get()+"4");
            System.out.println(future.get()+"5");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    /**
     * 线程中断
     */
    public void threadInterrupted(){
        //sleepThread sleep for 1000ms
        final Thread sleepThread = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        //busyThread keeps circling
        Thread busyThread = new Thread(){
            @Override
            public void run(){
                while(true){}
            }
        };
        sleepThread.start();
        busyThread.start();
        sleepThread.interrupt();
        busyThread.interrupt();
        while(sleepThread.isInterrupted()){};
        System.out.println("sleepThread is intertupted:" + sleepThread.isInterrupted());
        System.out.println("busyThread is intertupted:" + busyThread.isInterrupted());

    }
    /**
     *  线程等待
     */
    public void waitThread(){
        Thread headThread = Thread.currentThread();
        for(int i=0;i<10;i++){
            Thread curThread  = new JoinThread(headThread);
            curThread.start();
            headThread = curThread;
        }

    }
    /**
     * 守护线程
     */
    public void daemonThread(){
        Thread daemon = new Thread(){
            @Override
            public void run(){
                while(true){
                    try {
                        System.out.println("daemon is alive");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println("daemon is block");
                    }
                }
            }
        };
        daemon.setDaemon(true);
        daemon.start();

        //给daemon分配足够的时间片
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 *  join线程类
 */
class JoinThread extends Thread{
    private Thread thread;

    public JoinThread(Thread thread){
        this.thread = thread;
    }
    @Override
    public void run(){
        try {
            thread.join();
            System.out.println(thread.getName()+" is end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
/**
 * 继承Thread类，重写run方法
 */
class ExtendThread extends Thread{
    @Override
    public void run(){
        System.out.println("继承Thread类，重写run方法");
        super.run();
    }
}
/**
 *  实现Runnable接口
 */
class ImplThread implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable接口");
    }
}
/**
 *  实现callable接口
 */
class CallThread implements Callable{

    @Override
    public String call() throws Exception {
        return "实现Callable接口";
    }
}