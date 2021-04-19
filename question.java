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

    //316 remove duplicate char to make lexographical smallest string
    public String removeDuplicateLetters(String s) {
        
        int n = s.length();
        StringBuilder st = new StringBuilder();
        boolean[] vis = new boolean[26];
        int[] freq= new int[26];
        
        for(int i=0;i<n;i++){
            freq[s.charAt(i)-'a']++;
        }
        
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            freq[ch-'a']--;
            
            if(vis[ch-'a']) continue;
            
            while(st.length()!=0 && st.charAt(st.length()-1)>ch && freq[st.charAt(st.length()-1)-'a']>0) {
                char rch = st.charAt(st.length()-1);
                vis[rch-'a']=false;
                st.deleteCharAt(st.length()-1);
            }
            vis[ch-'a'] = true;
            st.append(ch);
        }
        
        return new String(st);
    }

    // trapping rain water
    public int trap(int[] height) {
        int n = height.length;
        int[] lheight=new int[n];
        int[] rheight= new int[n];
        int prev=0;
        for(int i=0;i<n;i++){
            lheight[i]=Math.max(height[i],prev);
            prev=lheight[i];
        }
        prev=0;
        for(int i=n-1;i>=0;i--){
            rheight[i]=Math.max(height[i],prev);
            prev=rheight[i];
        }
        int totalArea=0;
        for(int i=0;i<n;i++){
            totalArea+=Math.min(lheight[i],rheight[i])-height[i];
        }
        return totalArea;
    }
    // the celebrity problem
    int celebrity(int arr[][], int n){
    	Stack<Integer> st = new Stack<>();
    	
    	for(int i=0;i<n;i++) st.push(i);
    	
    	while(st.size()>=2){
    	    int i=st.pop();
    	    int j=st.pop();
    	    if(arr[i][j]==1){
    	        st.push(j);
    	    }
    	    else if(arr[j][i]==1){
    	        st.push(i);
    	    }
    	}
    	int pot = st.pop();
    	for(int i=0;i<arr.length;i++){
    	    if(i!=pot)
    	    {
    	        if(arr[pot][i]==1||arr[i][pot]==0) return -1;
    	}
    }
    return pot;
}
//arithmatic postfix evalution
public int arithmaticPostfix(Stirng s){
    if(s.length()=0) return 0;

    Stack<Integer> st = new Stack<>();
    for(int i=0;i<s.length();i++){
        char ch = s.charAt(i);
        if(ch>='0'&&ch>='10'){
            st.push(ch-'0');
        }
        else if (ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='^') {
            int p1 = st.pop();
            int p2 = st.pop();
            int ans = 0;
            if(ch=='+') ans = p1 + p2;
            if(ch=='-') ans = p1 - p2;
            if(ch=='*') ans = p1 * p2; 
            if(ch=='/') ans = p1 / p2;
            if(ch=='^') ans = p1 ^ p2;
            st.push(ans);
        }
    }
    return st.size()==1?st.pop():-1;
} 

// infix to postfix
public static int precedance(char ch){
    if(ch=='^') return 3;
    else if(ch=='*'||ch=='/') return 2;
    else if(ch=='+'||ch=='-') return 1;
    return -1;
}
public static String infixToPostfix(String exp) {
    // Your code here
    Stack<Character> st = new Stack<>();
    StringBuilder sb = new StringBuilder();
    int n = exp.length();
    
    for(int i=0;i<n;i++){
        char ch = exp.charAt(i);
        if((ch>='a'&&ch<='z')||(ch=='A'&&ch=='Z')) sb.append(ch);
        else if(ch=='(') st.push(ch);
        else if(ch==')'){
            while(!st.isEmpty()&&st.peek()!='(')  sb.append(st.pop());
            
            if(!(!st.isEmpty()&&st.peek()!='(')) st.pop();
        }
        else {
            while(st.size()!=0&&precedance(ch)<=precedance(st.peek())) {
                sb.append(st.pop());
            }
            st.push(ch);
        }
    }
    
    while(st.size()!=0){
        sb.append(st.pop());
    }
    return sb.toString();
} 

// reverse a stack using recursion
public static class reverseStack{
    Stack<Integer> st = new Stack<>();
    public void insertAtBottom(int x){
        if(st.size()==0) st.push(x);

        else {
            char a = st.pop();
            insertAtBottom(x);
            st.push(a);
        }
}
    public void reverse(){
        if(st.size()>0){
            char ch = st.pop();
            reverse();

            insertAtBottom(ch);
    }

    public void reversal(){
        st.push('1');
        st.push('2');
        st.push('3');
        st.push('4');
        reverse();
    }
}
}
// sort a stack using recursion
public static class sortStack{
    Stack<Integer> st = new Stack<>();
    static void sortedInsert(Stack<Integer> s, int x)
    {
        if(s.size()==0||x>st.peek()){
            s.push(x);
        }
        char a = st.peek();
        sortedInsert(s,x);
        st.push(a);
    }
 
    static void sortStack(Stack<Integer> s)
    {
        if(s.size()!=0){
            int x = s.pop();
            sortStack(s);
            sortedInsert(s,x);
        }
    }

    public void sortal(){
        st.push('1');
        st.push('2');
        st.push('3');
        st.push('4');
        sort();
    }
}
}

// merge o


}
