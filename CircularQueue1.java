public class CircularQueue {
    private int[] arr;
    private int front, rear, count, capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    public boolean isEmpty() { return count == 0; }

    public boolean isFull() { return count == capacity; }

    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = x;
        count++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int x = arr[front];
        front = (front + 1) % capacity;
        count--;
        return x;
    }

    public int front() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return arr[front];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue: ");
        for (int i = 0; i < count; i++) {
            System.out.print(arr[(front + i) % capacity] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(5);
        for (int i = 1; i <= 5; i++) q.enqueue(i * 10);
        q.display();
        q.enqueue(60);                       // full
        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Dequeued: " + q.dequeue());
        q.enqueue(60);                       // wraps around
        q.enqueue(70);
        q.display();
        System.out.println("Front: " + q.front());
        System.out.println("isFull: " + q.isFull() + ", isEmpty: " + q.isEmpty());
    }
}
