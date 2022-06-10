package hk.edu.polyu.comp.comp2021.cvfs.model;

import java.util.Stack;

/**
 * A stack with maximum size
 * @param <T> type
 */
public class SizedStack<T> extends Stack<T> {
    private int maxSize;

    /**
     * Initialize a SizedStack
     * @param size maximum size
     */
    public SizedStack(int size) {
        super();
        this.maxSize = size;
    }

    @Override
    public T push(T object) {
        //If the stack is too big, remove elements until it's the right size.
        while (this.size() >= maxSize) {
            this.remove(0);
        }
        return super.push(object);
    }
}

