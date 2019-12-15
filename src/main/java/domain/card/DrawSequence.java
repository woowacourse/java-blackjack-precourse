package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DrawSequence implements Sequence {
	private List<Integer> sequence;
	private int index = 0;

	public DrawSequence(int size) {
		Integer array[]=new Integer[size];
		for(int i=0;i<size;i++){
			array[i]=i;
		}
		sequence = new ArrayList<Integer>(Arrays.asList(array));
	}

	public void shuffle() {
		Collections.shuffle(sequence);
	}

	@Override
	public boolean hasNext() {
		return index < sequence.size();
	}

	@Override
	public int next() {
		int returnValue = sequence.get(index);
		index++;
		return returnValue;
	}
}
