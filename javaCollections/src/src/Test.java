package src;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
	public static void main(String[] args) {
//		MyLinkedList myLinkedList = new MyLinkedList();
//		
//		myLinkedList.add(2);
//		myLinkedList.add(3);
//		myLinkedList.add(5);
//		
//		System.out.println(myLinkedList);
//		System.out.println(myLinkedList.get(0));
//		System.out.println(myLinkedList.get(1));
//		System.out.println(myLinkedList.get(2));
//		
//		myLinkedList.remove(0);
//		System.out.println(myLinkedList);
		
		MyArrayList myArrayList = new MyArrayList();

		myArrayList.add(35);
		myArrayList.add(99);
		myArrayList.add(10);
		
		System.out.println(myArrayList);
		System.out.println(myArrayList.get(1));

		myArrayList.remove(1);
		
		System.out.println(myArrayList);

		myArrayList.remove(1);
		
		System.out.println(myArrayList);

		myArrayList.remove(0);
		
		System.out.println(myArrayList);
	}

}
