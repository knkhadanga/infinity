package main.miscs;

public class MyHashMap<K, V> {

    public static void main(String[] args) {
       MyHashMap<String, String> map = new MyHashMap<>();
       map.put("one", "hello");
       map.put("two", "world");
       map.put("thee", "test");
       map.put("four", "krushna");
       map.put("five", "nanda");
       map.put("six",  "khadanga");
       map.put("seven", "khadxanga");
       map.put("eight", "pppppppp");

       //System.out.println(map.getValue("theee"));
    }

    final int TABLE_SIZE = 17;
    MapNode<K,V> [] table;

    MyHashMap(){
        table = new MapNode[TABLE_SIZE];
    }

    void put (K key, V value){
        int hashCode = calculateHash(key);

        //find index
        int index = hashCode % TABLE_SIZE;
        MapNode node = table[index];

        if (node == null){
            table[index] = new MapNode();
            table[index].hashCode = hashCode;
            table[index].key = key;
            table[index].value = value;
            return;
        }else {
            MapNode temp = node;
            while(temp != null){
                if ((temp.hashCode == hashCode) && (temp.key == key)){
                    System.out.println("Updating existing entry");
                    temp.key = key;
                    temp.value = value;
                    temp.hashCode = hashCode;
                    return;
                } else{
                    temp = temp.next;
                }
            }

            temp = new MapNode();
            temp.hashCode = hashCode;
            temp.key = key;
            temp.value = value;

            //add to the first
            temp.next = table[index];
            table[index] = temp;

        }
    }

    V getValue (K key){
        int hashCode = calculateHash(key);
        int index = hashCode % TABLE_SIZE;

        System.out.println("Key - " + key + " hascode = " + hashCode);

        MapNode temp = table[index];
        System.out.println("11111111 -  " + temp.key);
        while (temp != null){
            if (temp.hashCode == hashCode && temp.key == key){
                V value = (V) temp.value;
                return value;
            }

            temp = temp.next;
        }

        return null;
    }


    int calculateHash(K key){
        String k = (String) key;
        int hash = 0;
        for (char c : k.toCharArray()){
            hash = hash * 37 + c;
        }

        return hash;
    }

}

class MapNode<K, V> {
    int hashCode;
    K key;
    V value;

    MapNode<K, V> next;
}