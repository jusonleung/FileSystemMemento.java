package hk.edu.polyu.comp.comp2021.cvfs.model;

import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cris;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;

/**
 * A FileSystem Memento
 */
public class FileSystemMemento {
    private Disk disk;
    private int diskCount;
    private final Cris cris;
    private Dir wDir;

    /**
     * Create a FileSystem Memento
     * @param disk the disk
     * @param diskCount the count of created disks
     * @param cris the storage of criteria
     * @param wDir the working directory
     */
    public FileSystemMemento(Disk disk, int diskCount, Cris cris, Dir wDir){
        this.disk = disk;
        this.diskCount = diskCount;
        this.cris = cris;
        this.wDir = wDir;
    }

    /**
     * @return the disk of this FileSystemMemento
     */
    public Disk getDisk() {
        return disk;
    }

    /**
     * @return the storage of criteria in this FileSystemMemento
     */
    public Cris getCris() {
        return cris;
    }

    /**
     * @return the count of created disks in this FileSystemMemento
     */
    public int getDiskCount() {
        return diskCount;
    }

    /**
     * @return the working directory of this FileSystemMemento
     */
    public Dir getwDir() {
        return wDir;
    }
}
