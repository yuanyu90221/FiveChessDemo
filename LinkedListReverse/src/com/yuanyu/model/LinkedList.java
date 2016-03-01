package com.yuanyu.model;

public class LinkedList <E>{
	Node<E> first, last;
	int size = 0;
	public void listAll(){
		Node<E> current = first;
		while(current!=null){
			System.out.print(current);
			current = current.next;
		}
		System.out.println();
	}
	
	public void addLast(Node<E> input){
		if(size == 0){
			first = input;
			last = input;
		}
		else{
			last.next = input;
			input.prev = last;
			last = input;
		}
		size++;
	}
	
	public void reverse(){
		Node<E> currentLast = last,
				currentFirst = first,
				current = first;
		while(current != null){
			Node orgNext = current.next;
	          current.next = current.prev;
	          current.prev = orgNext;
	          current = orgNext; 		
		}
		last = currentFirst;
		first = currentLast;
	}
}
