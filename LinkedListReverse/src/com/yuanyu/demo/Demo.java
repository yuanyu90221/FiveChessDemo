package com.yuanyu.demo;

import com.yuanyu.model.LinkedList;
import com.yuanyu.model.Node;
import com.yuanyu.model.Yuanyu;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Yuanyu> list = new LinkedList<Yuanyu>();
	    for(int i =0 ; i <5; i++)
	    {
	    	Yuanyu yu = new Yuanyu("json"+String.valueOf(i),10+i);
	    	Node<Yuanyu> test = new Node<Yuanyu>();
	    	test.setValue(yu);
	    	list.addLast(test);
	    }
	    list.listAll();
	    list.reverse();
	    list.listAll();
	}

}
