package leetcode;


import java.util.HashMap;
import java.util.Map;

public class easySolution {
    public static void main(String[] args) {
        int a=8;
        int b= 16;
        int c= a&b;
        System.out.print(c);
     }
}
class Solution {
    public int count=0;
    public int numberOfWays(int startPos, int endPos, int k) {
        if(k==1){
            if(endPos-startPos==1) return 1;
            else return 0;
        }
        if((endPos-startPos)%2!=k%2) return 0;
        else if((endPos-startPos)%3!=k%3) return 0;
        int[] tri = new int[k+1];
        for(int i=0;i<=k;i++) tri[i]=0;
        tri[0]=1;
        tri[1]=1;
        dfs(tri,1,k);
        int res =startPos+k+1;
        for(int i=0;i<=k;i++){
            if(endPos==res) return tri[i];
            res-=2;
        }
        return 0;

    }
    public void dfs(int[] tri,int deep,int stop){
        if(deep>stop) return;
        int now;
        for(int i=1;i<deep+1;i++){
            now=tri[i];
            if(i==1) tri[i]+=1;
            else{
                tri[i]+=now;
            }
        }
        dfs(tri,deep+1,stop);
    }
}