package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        return values().size();
    }

    @Override
    public V put(K key, V value) {
        List<V> list = map.get(key);
        V oldValue = null;

        if (list == null) {
            list = new ArrayList<>();
            map.put(key, list);

        } else {
            oldValue = list.get(list.size() - 1);
            if (list.size() == repeatCount) {
                list.remove(0);
            }
        }
        list.add(value);

        return oldValue;
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        List<V> list = map.get(key);
        V oldValue = null;

        if (list != null) {
            oldValue = list.get(0);
            list.remove(0);
            if (list.isEmpty())
                map.remove(key);
        }
        return oldValue;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        ArrayList<V> list = new ArrayList<V>();
        for (Map.Entry<K, List<V>> item : map.entrySet()
                ) {
            list.addAll(item.getValue());
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}