package com.natevaughan.bayes;

/**
 * Created by nate on 1/14/17.
 */
public class Tuple2<K, V> {

    public final K key;
    public final V value;

    public Tuple2(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
