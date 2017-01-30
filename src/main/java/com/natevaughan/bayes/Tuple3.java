package com.natevaughan.bayes;

/**
 * Created by nate on 1/14/17.
 */
public class Tuple3<K, I, V> {

    public final K key;
    public final I identifier;
    public final V value;

    public Tuple3(K key, I identifier, V value) {
        this.key = key;
        this.identifier = identifier;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public I getIdentifier() {
        return identifier;
    }

    public V getValue() {
        return value;
    }
}
