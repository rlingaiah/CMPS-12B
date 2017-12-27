/**
 * Rhea Lingaiah
 * Lab6 12M
 */
public class ListTest {
        public static void main(String[] args){
            List<String> testA = new List<String>();
            List<Integer> testB = new List<Integer>();
            //adds items to list
            testA.add(1, "one");
            testA.add(2, "wee");
            testA.add(3, "ooo");
            testA.add(4, "number");
            testA.add(5, "check");
            testA.add(6, "if ");
            testA.add(7,"same");
            testA.add(8,"test");


            testB.add(1, 1);
            testB.add(2, 2);
            testB.add(3, 3);
            testB.add(4, 4);
            testB.add(5, 5);
            testB.add(6, 6);

            testA.remove(3);
            testB.remove(4);

            System.out.println(testA.size());
            System.out.println(testB.size());

            System.out.println(testA.equals(testB));
            System.out.println("testA: " + testA);
            System.out.println("testB: "+ testB);
            testA.removeAll();
            System.out.println(testA.isEmpty());
            System.out.println("testA: " + testA);


        }

}
