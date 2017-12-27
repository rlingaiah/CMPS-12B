/**
 * Created by rlingaia@ucsc.edu on 8/9/17.
 */
import java.util.*;

public class Queue {
    private class Node{
        Object item;
        Node next;

        Node(Object item){
            this.item = item;
            next = null;
        }
    }
    //private fields
    private Node head;
    private Node back;
    private int numItems;
    //Constructor
    Queue(){
        head = null;
        back  = null;
        numItems = 0;
    }

    /*isEmpty()
     *Pre:none
     *Pos:returns true or flase if the queue is empty
     */
    public boolean isEmpty(){
        return (numItems==0);
    }
    //tells the size of the Queue
    public int length(){
        return numItems;
    }
    /*enqueue
     */
    public void enqueue(Object value){
        if(head == null){
            head = new Node(value);
            numItems++;
        }
        else{
            Node N = head;
            while( N.next != null){
                N = N.next;
            }
            N.next = new Node(value);
            back = N.next;
            numItems++;
        }
    }
    /*dequeue
     */
    public Object dequeue() throws QueueEmptyException{
        if(head == null){
            throw new QueueEmptyException("cannot have an empty queue stack");
        }
        else{
            Node N = head;
            head = N.next;
            numItems--;
            return N.item;
        }
    }
    /*peek
     */
    public Object peek() throws QueueEmptyException{
        if(head == null){
            throw new QueueEmptyException("cannot have an empty queue stack");
        }
        else{
            return head.item;
        }
    }
    /*dequeueAll
     */
    public void dequeueAll() throws QueueEmptyException{
        if(head == null){
            throw new QueueEmptyException("cannot have an empty queue stack");
        }
        else{
            head = null;
            back = null;
            numItems = 0;
        }
    }
    /*toString
     */
    public String toString(){
        String s="";
        Node N = head;
        while(N != null){
            s += N.item + " " ;
            N = N.next;
        }
        return s;
    }

}
