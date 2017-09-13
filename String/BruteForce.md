    
    package agstring;
    import sun.security.util.Length;
    public class BruteForce {
        //蛮力法的时间复杂度为O(mxn)
        //版本一：双重循环
    //  public static int matchOfBruteForce(String base, String sub){
    //      int baseLength = base.length();
    //      int subLength = sub.length();
    //      int m=0;int n=10;
    //      for(int i=0,j;i<baseLength-subLength+1;i++){
    //          for(j=0;j<subLength;j++){
    //              if(base.charAt(i+j) != sub.charAt(j)){
    //                  break;
    //              }
    //          }
    //          if(j == subLength){return i;}
    //      }
    //      return -1;
    //  }
        //版本二：单层循环
        public static int matchOfBruteForce(String base,String sub){
            int baseLength = base.length();
            int subLength = sub.length();
            int i = 0,j = 0;
            while(i<baseLength-subLength+1 && j<subLength){
                if(base.charAt(i+j) == sub.charAt(j)){
                    j++;
                }else{
                    i++;
                    j = 0;
                }
            }
            int result = j == subLength?i:-1;
            return result;
        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub
            String base = "fgdsjfsdfjalgorgj";
            String sub = "algo";
            int result = matchOfBruteForce(base, sub);
            System.out.println(result);
        }
    }
