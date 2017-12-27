/**
 * Rhea Lingaiah
 * rlingaia@ucsc.edu
 * 12B PA3
 * DictionaryTest will test each method in Dictionary.java
 */
public class DictionaryTest {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary();
        System.out.println(dict.isEmpty());
        System.out.println(dict.isEmpty());
        System.out.println(dict.size());
        dict.insert("2", "a");
        System.out.println(dict.isEmpty());
        dict.insert("5", "b");
        System.out.println(dict);
        System.out.println(dict.lookup("7"));
        System.out.println("********");
        System.out.println(dict.size());
        System.out.println(dict.lookup("2"));
        dict.delete("2");
        dict.delete("6");
        System.out.println(dict.size());
        System.out.println(dict);
        dict.makeEmpty();
        System.out.println(dict.size() + " " + dict.isEmpty());
    }
}
