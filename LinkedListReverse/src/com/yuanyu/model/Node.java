package com.yuanyu.model;

public class Node<E> {
	Node<E> prev, next;
	E value;
	
	public void setValue(E inValue){
		value = inValue;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	
	
}
