package springframework.guru.hashmapvshashtable.model;

public class Item {
    private String item;
    private int price;

    public Item() {
    }

    public Item(String item, int price) {
        this.item = item;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item='" + item + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        hashcode = price * 20;
        hashcode += item.hashCode();
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Item) {
            Item i = (Item)obj;
            return (i.item.equals(this.item) && i.price == this.price);
        }else {
            return false;
        }
    }
}
