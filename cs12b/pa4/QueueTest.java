/**
 * Created by rheareddy on 8/9/17.
 */
public class QueueTest {
        public static void main (String[] args){
            Queue test = new Queue();

            System.out.println(test.isEmpty());

            test.enqueue((int)3);
            test.enqueue((int)6);
            test.enqueue((int)2);
            test.enqueue((int)5);
            test.enqueue((int)1);
            test.enqueue((int)7);

            System.out.println(test.isEmpty());
            test.dequeueAll();

            System.out.println(test.length());
            test.enqueue((int)1);
            test.enqueue((int)7);

            test.dequeue();
            test.dequeue();
            //
            System.out.println(test.length());
            System.out.println(test);

        }
    }

