package algo.crackingthecoding.ch1;

public class Urlify {
    static String urlify(String s,int tl){
        if(s==null || s.length()==0)return null;
        String r="";
        char[] arr=s.toCharArray();
        int j=s.length()-1;
        for(int i=tl-1;i>=0;i--){
            if(arr[i]!=' '){
                arr[j--]=arr[i];
            }else{
                arr[j--]='0';
                arr[j--]='2';
                arr[j--]='%';
            }
        }
        return new String(arr);
    }
    public static void main(String[] args) {
        //"i am king   "
        /**
         * tl=9 12
         * i=11 ,arr[8]=g
         */
        System.out.println(urlify("i am king    ",9));
    }
}
