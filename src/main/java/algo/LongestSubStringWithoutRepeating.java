package algo;

public class LongestSubStringWithoutRepeating {
    /**
     * wert i abcda
     * @param s
     * @return
     */
    static int find(String s){
        int l=0,ans=0;
        int n = s.length();
        int[] index = new int[128];
        char[] arr = s.toCharArray();
        for(int i=0,j=0;i<n;i++){
           j = Math.max(index[arr[i]],j);
           ans=Math.max(ans,j-i+1);
           index[arr[i]]=i+1;
        }
        return ans;
    }
    public static void main(String[] args){
       System.out.println(find("abcda"));
    }
}
