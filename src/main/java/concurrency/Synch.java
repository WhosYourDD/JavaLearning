package concurrency;

public class Synch{
    static int count = 0;
    public static void main(String[] args){
        trySyn();
    }
    public static void trySyn(){
        for(int i=0;i<10;i++){
            Thread th = new Thread(){
                @Override
                public void run(){
                    synchronized (Thread.class){
                        for(int i=0;i<100000;i++){
                            count++;
                        }
                    }
                }
            };
            th.start();
        }
        try{
            Thread.sleep(100);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(count);
    }

}

