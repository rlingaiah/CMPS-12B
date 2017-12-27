/**
 * Rhea Lingaiah
 * Lab6 12M
 */
public class List<T> implements ListInterface<T>{

        private class Node{
            T item;
            Node next;

            Node(T x){
                item=x;
                next=null;

            }
        }
        private Node head;
        private int numItems;
        public List(){
            head=null;
            numItems=0;
        }
        private Node find(int index){
            Node N=head;
            for(int i=1; i<index;i++)
                N=N.next;
            return N;
        }
        /*isEmpty
         * Pos: returns false if the list is empty and true if it isnt
         */
        public boolean isEmpty(){
            return (numItems==0);
        }
        //returns the size of the list
        public int size(){
            return numItems;
        }
        /*get
         *Pre: has to be in the bounds of the list
         *Pos: returns the value at the position indicated by the index
         */
        public T get(int index) throws ListIndexOutOfBoundsException{
            if( index<1 || index>numItems){
                throw new ListIndexOutOfBoundsException("get():invalid index" +index);
            }
            Node N=find(index);
            return N.item;
        }
        /*add
         *Pre: index<1 || index>(numItems+1)
         *Pos: adds an element to the list
         */
        public void add(int index, T newItem) throws ListIndexOutOfBoundsException {
            if (index < 1 || index > (numItems + 1)) {
                throw new ListIndexOutOfBoundsException("add() invalid index" + index);
            }
            if (index == 1) {
                Node N = new Node(newItem);
                N.next = head;
                head = N;
            } else {
                Node P=find(index-1);
                Node C=P.next;
                P.next=new Node(newItem);
                P=P.next;
                P.next=C;
            }
            numItems++;
        }

        /*remove
        *Pre: index<1 || index>(numItems+1)
        *Pos: removes an element from the list
        */
        public void remove(int index) throws ListIndexOutOfBoundsException{
            if(index<1 || index>numItems){
                throw new ListIndexOutOfBoundsException("remove()invalid index:"+index);
            }
            if(index==1){
                Node N=head;
                head=head.next;
                N.next=null;
            }
            else{
                Node P= find(index-1);
                Node N=P.next;
                P.next=N.next;
                N.next=null;
            }
            numItems--;
        }

        public void removeAll(){
            head=null;
            numItems=0;
        }
        /*toString
         *puts the list into a string
         */
        public String toString(){
            String s="";
            for(Node N=head;N!=null;N=N.next){
                s += N.item.toString()+ " ";
            }
            return s;
        }
        /*equals
         *Pos: returns true or false if the contents of the list equal on another list
         */
        @SuppressWarnings("unchecked")
        public boolean equals(Object rhs){
            boolean eq =false;
            List<T> R = null;
            Node N=null;
            Node M=null;

            if(this.getClass() == rhs.getClass()){
                R = (List<T>) rhs;
                eq = (this.numItems == R.numItems);
                N=this.head;
                M=R.head;
                while( eq && (N!=null)){
                    eq = (N.item == M.item);
                    N=N.next;
                    M=M.next;
                }
            }
            return eq;
        }
    }
