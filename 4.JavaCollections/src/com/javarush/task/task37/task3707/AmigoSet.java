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
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
