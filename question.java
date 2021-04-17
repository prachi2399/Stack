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
        Arrays.fill(ans,n);
        Stack<Integer> st = new Stack<>();
        
        for(int i = n-1; i>=0; i--){
            while(st.size()>0 && nums[st.peek()]>=nums[i]){
                st.pop();
            }
            if(st.size()!=0)  ans[i]=st.peek();
            st.push(i);
        }
        
        for(int ele:ans)
            System.out.print(ele+" ");
        return ans;
    }

    public static int[] nextSmallerElementLeft(int[] nums){
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans,-1);
        Stack<Integer> st = new Stack<>();
        
        for(int i = 0; i<n; i++){
            while(st.size()>0 && nums[st.peek()]>=nums[i]){
                st.pop();
            }
            if(st.size()!=0)  ans[i]=st.peek();
            st.push(i);
        }
        for(int ele:ans)
            System.out.print(ele+" ");
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

    //1249
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        char[] arr=s.toCharArray();
        
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                st.push(i);
            }
            else  if(ch==')'){
                if(st.size()!=0&&s.charAt(st.peek())=='(') st.pop();
                else st.push(i);   
            }
        }
        
        while(st.size()!=0){
            arr[st.peek()]='#';
            st.pop();
        }
        
        StringBuilder ans=new StringBuilder();
        
        for(int i=0;i<s.length();i++){
            if(arr[i]!='#') {
                ans.append(arr[i]);
                }
        }
        return new String(ans);
        
    }

    // alt 1249
    public String minRemoveToMakeValid(String s) {
        ArrayDeque<Integer> st = new ArrayDeque<>();
        
        int n=s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                st.addFirst(i);
            else if (ch == ')') {
                if (st.size() != 0 && s.charAt(st.getFirst()) == '(')
                    st.removeFirst();
                else
                    st.addFirst(i);
            }
        }
        
        StringBuilder ans=new StringBuilder();
        
        for(int i=0;i<s.length();i++){
            if(st.size()!=0&&st.getLast()==i) {
                st.removeLast();
                continue;
                }
            ans.append(s.charAt(i));
        }
        return new String(ans);
    }

    //32
    public int longestValidParentheses(String s) {
        Stack<Integer> st=new Stack<>();
        st.push(-1);
        int n = s.length();
        int len=0;
        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            if(ch=='(') st.push(i);
             else if(ch==')'){
                 if(st.size()!=1 && s.charAt(st.peek())=='('){
                     st.pop();
                     len=Math.max(len,i-st.peek());
                 }
                 else st.push(i);
             }
        }
        return len;
    }

    // asteroids collide
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for(int ele:asteroids){
            if(ele>0) {
                st.push(ele);
                continue;
            }
            while(st.size()>0&&st.peek()>0&&st.peek()<-ele) st.pop();
            
            if(st.size()!=0&&st.peek()==-ele) st.pop();
            else if(st.size()==0||st.peek()<0) st.push(ele);
            else{
                
            }
        }
        ArrayList<Integer> ans=new ArrayList<>();
        while(st.size()>0){
            ans.add(0,st.pop());
        }
        int[] arr = new int[ans.size()];
        for(int i=0;i<ans.size();i++){
            arr[i]=ans.get(i);
        }
        return arr;
    }

    // largest area of histogram
    public int largestRectangleArea(int[] heights) {
        int[] nsol=nextSmallerElementLeft(heights);
        int[] nsor=nextSmallerElementRight(heights);
        
        int area=0;
        for(int i=0;i<heights.length;i++){
            int wt = nsor[i]-nsol[i]-1;
            int h=heights[i];
            area=Math.max(area,wt*h);
        }
        return area;
    }
    // leetcode 85
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        
        int maxRec=0;
        int[] height=new int[m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                char ch = matrix[i][j];
                height[j]=ch=='1'?height[j]+1:0;
            }
            maxRec=Math.max(maxRec,largestRectangleArea(height));
        }
        return maxRec;
    }
    
    public int largestRectangleArea(int[] heights) {
        int[] nsol=nextSmallerElementLeft(heights);
        int[] nsor=nextSmallerElementRight(heights);
        
        int area=0;
        for(int i=0;i<heights.length;i++){
            int wt = nsor[i]-nsol[i]-1;
            int h=heights[i];
            area=Math.max(area,wt*h);
        }
        return area;
    }

    //leetcode 221
    public int maximalSquare(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        
        int maxRec=0;
        int[] height=new int[m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                char ch = matrix[i][j];
                height[j]=ch=='1'?height[j]+1:0;
            }
            maxRec=Math.max(maxRec,largestRectangleArea(height));
        }
        return maxRec;
    }
    
    public int largestRectangleArea(int[] heights) {
        int[] nsol=nextSmallerElementLeft(heights);
        int[] nsor=nextSmallerElementRight(heights);
        
        int area=0;
        for(int i=0;i<heights.length;i++){
            int wt = nsor[i]-nsol[i]-1;
            int h=heights[i];
            area=Math.max(area,h>wt?wt*wt:h*h);
        }
        return area;
    }

    // largest area histogram alternate
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
            st.push(-1);
            int n= heights.length;
            int maxArea=0;
            for(int i=0;i<heights.length;i++){
                while(st.size()>1 && heights[st.peek()]>=heights[i]) {
                    int idx=st.pop();
                    int wt=i-st.peek()-1;
                    int h=heights[idx];
                    maxArea = Math.max(maxArea, wt*h);
            }
            st.push(i);
        }  
            while(st.peek()!=-1){
                int idx=st.pop();
                int h = heights[idx];
                int wt= n-st.peek()-1;
                maxArea = Math.max(maxArea, wt*h);
            }
        return maxArea;
    }

    // leetcode 402    
    public String removeKdigits(String num, int k) {
        ArrayList<Character> st = new ArrayList<>();
    
        for(int i=0;i<num.length();i++){
            char ch = num.charAt(i);
            while(st.size()!=0&&st.get(st.size()-1)>ch&&k>0){
                st.remove(st.size()-1);
                k--;
            }
            st.add(ch);
        }
        while(k-->0){
            st.remove(st.size()-1);
        }
        
        StringBuilder s = new StringBuilder();
        while(st.size()!=0&&st.get(0)=='0'){
            st.remove(0);
        }
        for(int i=0;i<st.size();i++){
            s.append(st.get(i));
        }
        return s.length()==0?"0":new String(s);
    }
}
