public class queue{

    private int[] arr;
    private int tos;
    private int NoOfElements;
    private int MaxCapacity;
    private int front;
    private int Back;

    protected void intialize(int size) {
        this.arr=new int[size];
        this.tos=-1;
        this.NoOfElements=-1;
        this.MaxCapacity=size;
    }
    public queue(){
        intialize(10);
    }

    public queue(int size){
        intialize(size);
    }

    public int Capacity() {
        return MaxCapacity;
    }

    public int size() {
        return this.NoOfElements;
    }

    public boolean isEmpty() {
        return this.NoOfElements == 0;
    }

    protected void QueueEmptyException() throws Exception {
        if (this.NoOfElements == 0)
            throw new Exception("QueueIsEmpty");
    }

    protected void QueueOverflowException() throws Exception {
        if (this.NoOfElements == this.MaxCapacity)
            throw new Exception("QueueOverflow");
    }

    protected void push_(int data) {
        this.arr[this.back] = data;
        this.back=(++this.back%this.MaxCapacity)
        this.NoOfElements++;
    }

    public void push(int data) {
        try {
            StackOverflowException();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("StackOverflow");
        }
        push_(data);
    }

    protected int front_() {
        return this.arr[this.front];
    }

    public int front() throws Exception {
        try {
            QueueEmptyException();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("StackIsEmpty");
        }
        return front_();
    }

    protected int pop_(){
        int rv = this.arr[this.tos];
        this.arr[this.front]=0;
        this.front = (++this.front % this.MaxCapacity);
        this.NoOfElements--;
        return ans;
    }

    public int pop() {
        try {
            QueueEmptyException();
        } catch (Exception e) {
            System.out.println("QueueIsEmpty");
        }
        return pop_();
    }
}
