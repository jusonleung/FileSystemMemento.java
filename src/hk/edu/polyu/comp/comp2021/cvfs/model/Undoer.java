package hk.edu.polyu.comp.comp2021.cvfs.model;

import java.util.Stack;

/**
 * A object that provide the function of undo and redo
 */
public class Undoer {
    private Stack<FileSystemMemento> undo;
    private Stack<FileSystemMemento> redo;

    /**
     * Initialize the undo and redo stack
     */
    public Undoer(){
        undo = new SizedStack<>(5);
        redo = new Stack<>();
    }

    /**
     * Push the FileSystem Memento into the undo stack and clear the redo stack
     * @param memento the FileSystem Memento
     */
    public void addUndo(FileSystemMemento memento){
        undo.push(memento);
    }

    /**
     * Push the FileSystem Memento into the redo stack
     * @param memento the FileSystem Memento
     */
    public void addRedo(FileSystemMemento memento){
        redo.push(memento);
    }

    /**
     * Pop the undo stack.
     */
    public void undoStackPop(){
        undo.pop();
    }

    /**
     * Pop the redo stack.
     */
    public void redoStackPop(){
        redo.pop();
    }

    /**
     * Clear the redo stack.
     */
    public void redoStackClear(){
        redo.clear();
    }

    /**
     * The undo function
     * @return undo result
     */
    public FileSystemMemento undo(){
        return undo.pop();
    }

    /**
     * The redo function
     * @return redo result
     */
    public FileSystemMemento redo(){
        return redo.pop();
    }

}
