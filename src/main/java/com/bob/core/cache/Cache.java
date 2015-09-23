package com.bob.core.cache;

import java.util.Set;

public interface Cache<V> {
	String getName();

	V get(String key);

	void set(String key, V value);

	void set(String key, V value, int expried);

	boolean del(String key);

	void clear();

	Set<String> keys();

}