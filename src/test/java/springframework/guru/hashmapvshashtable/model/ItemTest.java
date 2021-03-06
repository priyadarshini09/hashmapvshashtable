package springframework.guru.hashmapvshashtable.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ItemTest {

    Item item;
    Item item1;
    Item item2;
    Item item3;

    @Before
    public void setUp() {
        item = new Item("Water bottle", 20);
        item1 = new Item("Plate",30);
        item2 = new Item("Spoon",10);
        item3 = new Item("Glass", 15);
    }

    @After
    public void tearDown() {
        item = null;
        item1 = null;
        item2 = null;
        item3 = null;
    }

    @Test
    public void ItemWithHashMapTest() {
        HashMap<Item, Integer> hashMap = new HashMap<Item, Integer>();
        hashMap.put(item, 10);
        hashMap.put(item1, 5);
        hashMap.put(item2, 10);
        hashMap.put(item3, 3);
        hashMap.put(item1,16);
        System.out.println("Size of HashMap "+ hashMap.size());

        for(Map.Entry entry: hashMap.entrySet()) {
            System.out.println(entry.getKey().toString() + "-" + entry.getValue());
        }
        Item newItemAsKey = new Item("Water bottle", 20);
        Assert.assertTrue(hashMap.containsKey(newItemAsKey));
    }

    @Test
    public void ItemWithHashtableTest() {
        Hashtable<Item, Integer> hashtable = new Hashtable<Item, Integer>();
        hashtable.put(item, 10);
        hashtable.put(item1, 5);
        hashtable.put(item2, 10);
        hashtable.put(item3, 3);
        hashtable.put(item1,16);

        System.out.println("Size of Hashtable "+ hashtable.size());
        for(Map.Entry entry: hashtable.entrySet()) {
            System.out.println(entry.getKey().toString() + "-" + entry.getValue());
        }
        Item itemAsKeyInHashtable = new Item("Water bottle", 20);
        Assert.assertTrue(hashtable.containsKey(itemAsKeyInHashtable));
        Assert.assertTrue(hashtable.containsValue(hashtable.get(itemAsKeyInHashtable)));
    }

    @Test
    public void ConcurrentModificationExceptionTest() {
        HashMap<Item, Integer> hashMap = new HashMap<Item, Integer>();
        hashMap.put(item, 10);
        hashMap.put(item1, 5);
        hashMap.put(item2, 10);
        for(Map.Entry<Item, Integer> entry: hashMap.entrySet()) {
            Integer value = entry.getValue();
            if(value == 5) {
                hashMap.remove(entry.getKey());
            }
        }
    }

    @Test
    public void ConcurrentModificationExceptionHandleTest() {
        HashMap<Item, Integer> hashMap = new HashMap<Item, Integer>();
        hashMap.put(item, 10);
        hashMap.put(item1, 5);
        hashMap.put(item2, 10);
        for(Iterator<Map.Entry<Item, Integer>> it = hashMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Item, Integer> entry = it.next();
            Integer value = entry.getValue();
            if(value == 5) {
                it.remove();
                System.out.println("Item with value 5 safely removed from HashMap");
            }
        }
    }

    @Test
    public void ExceptionWithHashtableTest() {
        Hashtable<Item, Integer> hashtable = new Hashtable<Item, Integer>();
        hashtable.put(item, 10);
        hashtable.put(item1, 5);
        hashtable.put(item2, 10);
        Enumeration<Item> en = hashtable.keys();
        while(en.hasMoreElements()) {
            Item key = en.nextElement();
            Integer value = hashtable.get(key);
            if(key.equals(item)) {
                hashtable.remove(key);
                System.out.println("Item safely removed from Hashtable");
            }
        }
//        for(Map.Entry<Item, Integer> entry: hashtable.entrySet()) {
//            Integer value = entry.getValue();
//            System.out.println("sadsdsfs" + value);
//            if(value == 5) {
//                System.out.println("dhsdshf");
//                hashtable.remove(entry.getKey());
//                System.out.println("Item with value 5 safely removed from Hashtable");
//            }
//        }
    }

    @Test
    public void NullCheckInHashMapTest() {
        HashMap<Item, Integer> hashMap = new HashMap<Item, Integer>();
        hashMap.put(item, 10);
        hashMap.put(item1, 5);
        hashMap.put(null, null);
        System.out.println("Null key "+ hashMap.get(null));
    }

    @Test
    public void NullCheckInHashtableTest() {
        Hashtable<Item, Integer> hashtable = new Hashtable<Item, Integer>();
        hashtable.put(item, 10);
        hashtable.put(item1, 5);
        hashtable.put(null, null);
        System.out.println("Null key "+ hashtable.get(null));
    }
}
