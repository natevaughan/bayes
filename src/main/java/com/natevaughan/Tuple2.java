package com.natevaughan;

/**
 * @author Nate Vaughan
 */
public class Tuple2<K, V> {
	private final K first;
	private final V second;

	public Tuple2(K first, V second) {
		this.first = first;
		this.second = second;
	}

	public K getFirst() {
		return first;
	}

	public V getSecond() {
		return second;
	}
}
