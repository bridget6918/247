package studio3;

import java.util.LinkedList;
import java.util.List;

public class UnorderedList<T extends Comparable<T>> implements PriorityQueue<T> {

	public LinkedList<T> list;
	
	public UnorderedList() {
		list = new LinkedList<T>();
	}
	
	@Override
	public boolean isEmpty() {
		if(list.isEmpty()) {
			return true;
		}
		else return false;
	}

	@Override
	public void insert(T thing) {
		list.addLast(thing);
	}

	@Override
	public T extractMin() {
		
	}

}
