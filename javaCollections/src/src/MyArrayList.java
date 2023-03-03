package src;

import java.util.Arrays;

public class MyArrayList {
	private int[] array = new int[1];
	private boolean added = false;

	public MyArrayList() {
	
	}
	
	public void add(int value) {
		if (added == false) {
			array[0] = value;
			added = true;
		}
		else {
			int[] arrayToDelete = array;
			array = new int[arrayToDelete.length+1];
			for (int i=0; i<array.length-1; i++) {
				array[i] = arrayToDelete[i];
			}
			array[array.length-1] = value;
		}
	}
	
	public int get(int index) {
		return array[index];
	}
	
	public void remove(int index) {
		int[] arrayToDelete = array;
		array = new int[arrayToDelete.length-1];
		
		for (int i=0; i<index; i++) {
			array[i] = arrayToDelete[i];
		}
		
		for(int i = index; i<array.length; i++) {
			array[i] = arrayToDelete[i+1];
		}
	}
	
	public String toString() {
		return Arrays.toString(array);
	}
}
