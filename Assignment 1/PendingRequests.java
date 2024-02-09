import Includes.*;

public class PendingRequests {
    private int length = 0;
    private Node<RequestData> front;
    private Node<RequestData> back;

    public boolean insert(Node<RequestData> insnode) {
        if(length==0){
            front =insnode;
            back=insnode;
        }
        else{
            back.next=insnode;
            insnode.previous=back;
            back=insnode;
        }
        length++;
        return true;
    }

    public boolean delete(Node<RequestData> delnode) {
        Node<RequestData> temp=front;
        while(temp!=null && temp!=delnode){
            temp=temp.next;
        }
        if(temp==null){
            return false;
        }
        else if(temp==front){
            front.next.previous=null;
            front=front.next;
            temp.next=null;
        }
        else if(temp==back){
            back.previous.next=null;
            back=back.previous;
            temp.previous=null;
        }
        else{
            temp.previous.next=temp.next;
            temp.next.previous=temp.previous;
            temp.next=null;
            temp.previous=null;
        }
        length--;
        return true;
    }

    public Node<RequestData> find(int ISBN) {
        Node<RequestData> nrd = front;
        while(nrd!=null && nrd.data.ISBN!=ISBN){
            nrd=nrd.next;
        }
        return nrd;
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
