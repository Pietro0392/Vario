package engine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

class Deck implements Iterable<Card>{
    private final ArrayList<Card> mazzo = new ArrayList<>(52);
    private int indexCounter = 0;
    public Deck(){
        for(int i=2;i<15;i++)
            for(int j=0;j<4;j++)
                mazzo.add(indexCounter++,new Card(i,j));
    }
    public void mescola(){
        Collections.shuffle(mazzo);
    }
    public Card rimuoviCartaInCima(){
        if (mazzo.size() == 0)
            throw new RuntimeException("Mazzo vuoto");
        else return mazzo.remove(--indexCounter);
    }
    @Override
    public Iterator<Card> iterator() {
        return new DeckIterator(mazzo.iterator());
    }
    public int size(){
        return mazzo.size();
    }
}
