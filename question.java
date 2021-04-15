import java.util.Stack;
public class question{
    public static void main(String[] args){
        int n = 10;
        int[] nums = {3,2,-1,4,5,-3,-7,-6,-4,6,5};
        int[] ans=nextSmallerElementLeft(nums);
        for(int ele:ans){
            System.out.print(ele + " ");
        }
    }

    public static int[] nextGreaterElementRight(int[] nums){// O(2N)=o(N);
        int n = nums.length;
        int[] ans = new int[n];
        for(int i=0;i<n;i++) ans[i] = -1;
        Stack<Integer> st = new Stack<>();

        st.push(nums[n-1]);
        
        for(int i = n-2; i>=0; i--){
            while(st.size()>0 && nums[st.peek()]<nums[i]){
                st.pop();
            }
            if(st.size()!=0)  ans[i]=st.peek();
            st.push(i);
        }
        return ans;
    }

    public static int[] nextGreaterElementLeft(int[] nums){
        int n = nums.length;
        int[] ans = new int[n];
        for(int i=0;i<n;i++) ans[i] = -1;
        Stack<Integer> st = new Stack<>();

        st.push(nums[0]);
        
        for(int i = 0; i<n; i++){
            while(st.size()>0 && nums[st.peek()]<nums[i]){
                st.pop();
            }
            if(st.size()!=0)  ans[i]=st.peek();
            st.push(i);
        }
        return ans;
    }

    public static int[] nextSmallerElementRight(int[] nums){
        int n = nums.length;
        int[] ans = new int[n];
        for(int i=0;i<n;i++) ans[i] = -1;
        Stack<Integer> st = new Stack<>();

        st.push(nums[n-1]);
        
        for(int i = n-2; i>=0; i--){
            while(st.size()>0 && nums[st.peek()]>nums[i]){
                st.pop();
            }
            if(st.size()!=0)  ans[i]=st.peek();
            st.push(i);
        }
        return ans;
    }

    public static int[] nextSmallerElementLeft(int[] nums){
        int n = nums.length;
        int[] ans = new int[n];
        for(int i=0;i<n;i++) ans[i] = -1;
        Stack<Integer> st = new Stack<>();

        st.push(nums[0]);
        
        for(int i = 0; i<n; i++){
            while(st.size()>0 && nums[st.peek()]>nums[i]){
                st.pop();
            }
            if(st.size()!=0)  ans[i]=st.peek();
            st.push(i);
        }
        return ans;
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans,-1);
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i<2*n; i++){
            while(st.size()>0 && nums[st.peek()]<nums[i%n]){
                ans[st.peek()]=nums[i%n];
                st.pop();
            }
            st.push(i%n);
        }
        return ans;
    }
    //leetcode 901
    Stack<int[]> st;
    int day;
    public StockSpanner() {
    st = new Stack<>();
    day=0;
        st.push(new int[]{-1,-1});
    }
    
    public int next(int price) {
        while(st.peek()[1]!=-1 && st.peek()[1]<=price){
            st.pop();
        }
        int span = day-st.peek()[0];
        st.push(new int[]{day++,price});
        return span;
    }

    //leetcode 901 alternate
    Stack<int[]> st;
    int day;
    public StockSpanner() {
    st = new Stack<>();
    day=0;
        st.push(new int[]{-1,-1});
    }
    
    public int next(int price) {
        while(st.peek()[1]!=-1 && st.peek()[1]<=price){
            st.pop();
        }
        int span = day-st.peek()[0];
        st.push(new int[]{day++,price});
        return span;
    }
    //20
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        
        boolean res=false;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(s.charAt(i)=='('||s.charAt(i)=='{'||s.charAt(i)=='['){
                st.push(s.charAt(i));
            }
            else {
                if(st.size()==0) return false;
            else if(s.charAt(i)==')'&& st.peek()!='('){
               return false;
            }
            else if(s.charAt(i)=='}'&& st.peek()!='{'){
               return false;
            }
            else if(s.charAt(i)==']'&& st.peek()!='['){
               return false;
            }
            else st.pop();
            }
        }
        return st.size()==0;
    }

    //946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        
        Stack<Integer> st = new Stack<>();
        
        for(int i=0,j=0;i<pushed.length;i++){
            st.push(pushed[i]);
            while(st.size()>0&&st.peek()==popped[j]){
                st.pop();
                j++;
            }
        }
        return st.size()==0;
    }
}
