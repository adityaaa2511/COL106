import Includes.*;

public class RequestQueue {
    private Node<RequestData> front;
    private Node<RequestData> back;
    private int length=0;

    public RequestData getFront() {
        return this.front.data;
    }

    public int getLength() {
        return this.length;
    }

    public void push(int ISBN, int UserID) {
        RequestData temp= new RequestData(ISBN,UserID);
        Node<RequestData> node1 = new Node<RequestData>();
        node1.data = temp;
        if(length==0){
            front=node1;
            back=node1;
            length++;
        }
        else{
            back.next=node1;
            node1.previous=back;
            back=node1;
            length++;
        }
        return;
    }

    public void pop() { 
        if(length==0){
            return;
        }
        else if(length==1){
            front=null;
            back=null;
            length--;
        }
        else{
            Node<RequestData> temp=front.next;
            front.next.previous=null;
            front.next=null;
            front=temp;
            length--;
        }
        return;
    }

    public String toString(){
        Node<RequestData> temp = front;
        String s = "Length: " + length + "\n";
        while(temp != null){
            s+=temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
}
