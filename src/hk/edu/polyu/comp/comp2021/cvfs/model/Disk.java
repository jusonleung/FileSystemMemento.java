package hk.edu.polyu.comp.comp2021.cvfs.model;

import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;
import java.io.Serializable;

/**
 * A disk
 */
public class Disk implements Serializable {

    private String name;
    private int sizeLimit;
    private Dir dir;

    /**
     * Initialize a disk
     * @param sizeLimit size limit of disk
     * @param name name of disk
     */
    public Disk(int sizeLimit, String name){
        if (sizeLimit<0)
            throw new IllegalArgumentException();

        this.name = name;
        this.sizeLimit = sizeLimit;
        dir = new Dir(name + ":");
    }

    /**
     * @return root directory of disk
     */
    public Dir getDir(){
        return dir;
    }

    /**
     * @return size limit of disk
     */
    public int getSizeLimit() {
        return sizeLimit;
    }

    /**
     * @return size of disk
     */
    public int getSize(){return dir.getSize();}

    public String toString(){
        return name;
    }

}
