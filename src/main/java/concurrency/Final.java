package concurrency;

public class Final {
    /**
     * 类变量可以在静态初始化块中赋值
     */
    public final static int a;
    /**
     * 实例变量可以在非静态初始化块中赋值
     */
    private final boolean flag;
    /**
     * 实例变量可以在构造器中赋值
     */
    private final int b;
    static{
        a = 1;
    }
    {
        flag = true;

    }

    public static void main(String[] args) {
    }
    public Final(int b){
        this.b=b;
    }
    public void portVar(){
        final int c = 0;
        final int d;
        d = 1;
    }

    /**
     * 被final修的方法在子类中无法重写
     */
    public final void test(){
        int s = 1;
    }

    /**
     *
     * 被final修饰的方法是可以重载的
     */
    public final String test(String b){
        return b;
    }
}
class Child extends Final{
    private Integer a = 1;
    private String s ="aaa";
    {
        a = 2;
        s = "ccc";
    }
    public Child(int b) {
        super(b);
    }

}

