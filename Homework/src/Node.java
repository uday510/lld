public class Node {
    int x;
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
    public Node(Node n) {
        Node newNode = new Node(n.data);
        newNode.next = n.next;
    }

    class ping {
        public void pinger(int val) {
            x = val;
        }
    }
}
