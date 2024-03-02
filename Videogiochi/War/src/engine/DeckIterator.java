package engine;

import java.util.Iterator;

class DeckIterator implements Iterator<Card>{
    private final Iterator<Card> arrayIT;
    public DeckIterator(Iterator<Card> arrayIT){
        this.arrayIT = arrayIT;
    }
    @Override
    public boolean hasNext() {
        return arrayIT.hasNext();
    }
    @Override
    public Card next() {
        return arrayIT.next();
    }
}
