package concurrency;

import java.util.concurrent.locks.*;

/**
 锁是用来控制多个线程访问共享资源的方式，一般来说，一个锁能够防止多个线程同时访问共享资源。在Lock接口出现之前，java程序主要是靠synchronized关键字实现锁功能的，而java SE5之后，并发包中增加了lock接口，它提供了与synchronized一样的锁功能。
 */
public class LockRef {
    static int a = 0;
    static Lock lock = new ReentrantLock();
    Condition c;
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static void main(String[] args) throws InterruptedException {
        lockSupportRef();
    }
    public static void lockSupportRef() throws InterruptedException {
        Thread thread = new Thread(()->{
           LockSupport.park();
           System.out.println(Thread.currentThread().getName()+" is waking");
        });
        thread.start();
        Thread.sleep(8000);
        LockSupport.unpark(thread);
    }
    public static void reentrant() throws InterruptedException {
        for(int i=0;i<10;i++){
//            Lock lock = new ReentrantLock();
//            lock.lock();
            Thread th = new Thread(){
                @Override
                public void run(){
                    for(int i=0;i<1000;i++){
                        lock.lock();
                        try{
                            a++;
                        }finally {
                            lock.unlock();
                        }
                    }

                }
            };
            th.start();
        }
        //为什么这里要使用sleep方法？？？？？？？？
        Thread.sleep(100);
        System.out.println("a:"+a);
    }
}

