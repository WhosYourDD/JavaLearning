package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class hardSolution {
    /**
     * 76.最小覆盖子串
     */
    Map<Character,Integer> ori = new HashMap<>();
    Map<Character,Integer> map = new HashMap<>();
    public String minWindow(String s,String t){
        int sLen=s.length();
        int tLen=t.length();
        for(int i=0;i<tLen;i++){
            char c = s.charAt(i);
            ori.put(c, ori.getOrDefault(c,0)+1);
        }
        int l=0,r=-1;
        int len=Integer.MAX_VALUE,ansL=-1,ansR=-1;
        while(r<sLen) {
            r++;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);
                }
                l++;
            }
        }
        return ansL==-1?"":s.substring(ansL,ansR);
    }
    public boolean check(){
        Iterator it = ori.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            Character key = (Character) entry.getKey();
            Integer val=(Integer) entry.getValue();
            if(map.getOrDefault(key,0)<val){
                return false;
            }
        }
        return true;
    }
}
