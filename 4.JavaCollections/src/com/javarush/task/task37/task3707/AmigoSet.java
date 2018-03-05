package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {

    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<E, Object>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<E, Object>(Math.max(16, (int) Math.ceil(collection.size() / .75f)));
        addAll(collection);
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public Object clone() {
        try {
            HashMap<E, Object> newMap = (HashMap<E, Object>) map.clone();
            AmigoSet<E> newAmigoSet = new AmigoSet<E>();
            newAmigoSet.map = newMap;
            return newAmigoSet;
        } catch (Exception e) {
            throw new InternalError();
        }
    }
}
