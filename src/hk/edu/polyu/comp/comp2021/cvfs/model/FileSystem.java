package hk.edu.polyu.comp.comp2021.cvfs.model;

import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cris;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Doc;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.InitSize;

import java.io.*;
import java.util.Stack;


/**
 * A FileSystem
 */
public class FileSystem implements java.io.Serializable{
    private Disk disk;
    private Dir workingDir;
    private final Stack<Dir> dirStack;
    private Cris cris;
    private int diskCount;



    /**
     * Initialize a FileSystem
     */
    public FileSystem(){
        cris = new Cris();
        dirStack = new Stack<>();
    }

    /**
     * @return true if disk is null
     */
    public boolean isEmptyDisk(){
        return disk == null;
    }

    /**
     * Set the woking directory to the given name directory
     * @param name the name of directory that want to open
     */
    public void openDir(String name){
        if (name == null)
            throw new IllegalArgumentException();
        int result = workingDir.searchName(name);
        if (!(workingDir.getFileObject(result) instanceof Dir))
            throw new IllegalArgumentException();

        dirStack.push(workingDir);
        workingDir = (Dir) workingDir.getFileObject(result);
    }

    /**
     * Return working directory to its parent directory
     * set the parent directory of the working directory as the new working directory
     */
    public void closeDir(){
        if (dirStack.peek() == null)
            throw new IllegalArgumentException();

        workingDir = dirStack.pop();
    }

    /**
     * @return the current working directory
     */
    public Dir getDir(){
        return workingDir;
    }

    /**
     * Add a document to working directory
     * @param doc the document to be added
     * @throws IllegalArgumentException if disk size will excess its size limit when adding the document
     */
    public void addDoc(Doc doc) {
        boolean canCreate = disk.getSize() + doc.getSize() <= disk.getSizeLimit();
        if (!canCreate) {
            System.out.print("** Excess disk size limit - ");
            throw new IllegalArgumentException();
        }

        workingDir.add(doc);
    }

    /**
     * Add a directory to working directory
     * @param name name of directory
     * @throws IllegalArgumentException if disk size will excess its size limit when adding the directory
     */
    public void addDir(String name) {
        boolean canCreate = disk.getSize() + InitSize.DIR.getSize() <= disk.getSizeLimit();
        if (!canCreate) {
            System.out.print("** Excess disk size limit - ");
            throw new IllegalArgumentException();
        }

        workingDir.add(new Dir(name));
    }

    public String workingDirToString(){
        Object[] temp = dirStack.toArray();
        StringBuilder result = new StringBuilder();
        for (Object o:temp){
            result.append(o.toString()).append("\\");
        }
        result.append(workingDir.toString()).append("\\");
        return result.toString();
    }

    /**
     * Create a memento of the this FileSystem
     * @return the current FileSystem's memento
     */
    public FileSystemMemento createMemento(){
        String fileName = "temp\\fileSystemMemo.txt";
        serialize(fileName);
        FileSystem temp = deserialize(fileName);
        return new FileSystemMemento(temp.getDisk(),temp.getDiskCount(),temp.getCris(),temp.getwDir());
    }

    /**
     * Copy the provided FileSystem's memento to the this FileSystem
     * @param memento the provided FileSystem's memento
     */
    public void setMemento(FileSystemMemento memento){
        this.disk = memento.getDisk();
        this.workingDir = memento.getwDir();
        this.cris = memento.getCris();
        this.diskCount = memento.getDiskCount();
    }


    /**
     * Serialize this FileSystem to a file
     * @param fileName the name of file that contain the serialized FileSystem
     */
    public void serialize(String fileName){

        try{
            File file = new File(fileName);
            file.createNewFile();
            FileOutputStream stream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            stream.close();
        } catch (IOException ignored) {
        }

    }


    /**
     * Deserialize the provided file to the FileSystem
     * @param fileName the provided file's name
     * @return the FileSystem from deserialize the provided file
     */
    public FileSystem deserialize(String fileName){
        FileSystem fileSystem = null;
        try{
            File file = new File(fileName);
            file.createNewFile();
            FileInputStream stream = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(stream);
            fileSystem = (FileSystem) input.readObject();
            input.close();
            stream.close();
        } catch (ClassNotFoundException | IOException ignored) {
        }
        return fileSystem;
    }

    /**
     * Add the count of disk created
     * @param num the count of disk created
     */
    public void addDiskCount(int num){
        diskCount += num;
    }

    /**
     * Set a new disk of this FileSystem
     * @param disk a new disk
     */
    public void setDisk(Disk disk){
        this.disk = disk;
    }

    /**
     * Set a new working directory of this FileSystem
     * @param wDir a new working directory
     */
    public void setwDir(Dir wDir){
        this.workingDir = wDir;
    }

    /**
     * @return the working directory of this FileSystem
     */
    public Dir getwDir(){
        return workingDir;
    }

    /**
     * @return the current disk of this FileSystem
     */
    public Disk getDisk() {
        return disk;
    }

    /**
     * @return the storage of criteria in this FileSystem
     */
    public Cris getCris(){
        return cris;
    }

    /**
     * @return the count of created disks in this FileSystem
     */
    public int getDiskCount(){
        return diskCount;
    }

    @Override
    public String toString() {
        return "FileSystem{" +
                "diskCount=" + diskCount +
                '}';
    }
}
