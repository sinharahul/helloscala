package algo.crackingthecoding.ch1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PalindromicPermutations {
    static Set<String> palindromePermutations(String s){
        Set<String> r =findAllPermutations(s);
        r=r.stream().filter(str-> (str.equals(reverse(str)))).collect(Collectors.toSet());
        return r;
    }

    private static Set<String> findAllPermutations(String s) {
        Set<String> set = new HashSet<>();
        set.add(s);
        char[] arr=s.toCharArray();
        for(int i=0;i<arr.length-1;i++) {
            for(int j=i+1;j<arr.length;j++){
                set.add(swap(arr,i,j));
            }
        }
        return set;
    }

    private static String swap(char[] arr, int i, int j) {
        char[] copy=new char[arr.length];
        System.arraycopy(arr,0,copy,0,arr.length);
        char temp=copy[i];
        copy[i]=copy[j];
        copy[j]=temp;
        return new String(copy);
    }

    private static String reverse(String s){
        char[] arr=s.toCharArray();
        int start=0,end=arr.length-1;
        while(start<end){
            char temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;end--;
        }
        return new String(arr);
    }
    public static void main(String[] args) {

    }
}
