package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class mediumSolution {

    List<List<Integer>> ans=new ArrayList<>();
    List<Integer> list=new ArrayList<>();
    public List<List<Integer>> combine(int n,int k){
        combineDFS(1,n,k);
        return ans;
    }
    public void combineDFS(int cur,int n,int k){
        if(list.size()+(cur-n+1)<k){
            return;
        }
        if(list.size()==k){
            ans.add(new ArrayList<>(list));
            return;
        }

        list.add(cur);
        combineDFS(cur+1,n,k);
        list.remove(list.size()-1);

        combineDFS(cur=1,n,k);
    }

}
