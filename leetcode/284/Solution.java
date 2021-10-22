// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    Integer pointer = null;
    
    public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iterator = iterator;
        if(this.iterator.hasNext()) this.pointer = this.iterator.next();
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return this.pointer;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    Integer result = pointer;
        if(this.iterator.hasNext()) this.pointer = this.iterator.next();
        else this.pointer = null;
        return result;
	}
	
	@Override
	public boolean hasNext() {
	    return pointer != null;
	}
}