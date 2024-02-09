import java.util.*;

import Includes.*;

public class LibraryStorage {
    private HashMap<Integer, BookData> storage;
    private RequestQueue rqQueue;
    private PendingRequests prLinkedList;

    public LibraryStorage() {
        storage = new HashMap<Integer, BookData>();
        for (int i=100001; i<100011; i++) {
            BookData book = new BookData();
            MyDate dateor = new MyDate();
            dateor.month = 0;
            dateor.year = 0;
            book.BorrowedStatus = false;
            book.BorrowedByUserID = 0;
            book.ISBN = i;
            book.Publisher = "publisher";
            book.Author = "author";
            book.DateOfReturn = dateor;
            storage.put(i, book);
        }

        rqQueue = new RequestQueue();
        prLinkedList = new PendingRequests();
    }

    public void push(int ISBN, int UserID) {
        rqQueue.push(ISBN, UserID);
        return;
    }

    public boolean processQueue() {
        RequestData curr=rqQueue.getFront();
        Iterator<Integer> it=storage.keySet().iterator();
        while(it.hasNext()){
            int isbn=it.next();
            if(isbn==curr.ISBN){
                System.out.println(isbn);
                BookData bok=storage.get(isbn);
                bok.BorrowedStatus=true;
                bok.BorrowedByUserID=curr.UserID;
                if(curr.RequestDate.month!=12){
                    bok.DateOfReturn.month=curr.RequestDate.month+1;
                }
                else{
                    bok.DateOfReturn.month=1;
                    bok.DateOfReturn.year=curr.RequestDate.year+1;
                }
                storage.remove(isbn);
                rqQueue.pop();                
                return true;
            }
        }
        Node<RequestData> n= new Node<RequestData>();
        n.data=curr;
        prLinkedList.insert(n);
        rqQueue.pop();
        return false;
    }

    public boolean processReturn(BookData book) {          // I have assumed this takes BookData object as argument, can also work with ISBN perhaps
        if(prLinkedList.find(book.ISBN)!=null){
            Node<RequestData> n=prLinkedList.find(book.ISBN);
            book.BorrowedByUserID=n.data.UserID;
            book.BorrowedStatus=true;
            if(book.DateOfReturn.month==12){
                book.DateOfReturn.month=1;
                book.DateOfReturn.year=book.DateOfReturn.year+1;
            }
            else{
                book.DateOfReturn.month=book.DateOfReturn.month+1;
            }
            prLinkedList.delete(n);
            return true;
        }
        book.DateOfReturn.month = 0;
        book.DateOfReturn.year=0;
        book.BorrowedStatus = false;
        book.BorrowedByUserID = 0;
        storage.put(book.ISBN,book);
        return false;
    }

    public String rqQueueToString(){
        return rqQueue.toString();
    }

    public String prLinkedListToString(){
        return prLinkedList.toString();
    }
}
