package hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject;

/**
 * Enumeration InitSize represent the initial size of files in a file system.
 */
public enum InitSize {
    /** Initial size of document */
    DOC(40),
    /** Initial size of directory */
    DIR(40);

    private final int size;

    InitSize(int size){
        this.size = size;
    }

    /**
     * Get the initial size of this file.
     * @return the initial size
     */
    public int getSize(){
        return size;
    }
}
