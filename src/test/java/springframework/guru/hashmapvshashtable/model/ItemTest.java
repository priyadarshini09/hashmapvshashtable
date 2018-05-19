package springframework.guru.hashmapvshashtable.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

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
            Item i = entry.getKey();
            if(i.equals(item1)) {
                hashMap.remove(i);
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
            Item i = entry.getKey();
            if(i.equals(item1)) {
                it.remove();
                System.out.println("Item1 safely removed from HashMap");
            }
        }
    }


}
