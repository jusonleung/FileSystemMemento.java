package hk.edu.polyu.comp.comp2021.cvfs.model.criteria;

import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.FileObject;

/**
 * The Cri class represents a criteria
 */
public abstract class Cri implements java.io.Serializable{
    private final String name;

    /**
     * Construct a criterion. All criterion extend this class must contain a name.
     * @param name The criterion's name
     * @throws IllegalArgumentException If name is null
     */
    public Cri(String name){
        if (name == null)
            throw new IllegalArgumentException();

        this.name = name;
    }

    /**
     * Search the directory to print the files that meet this criterion.
     * @param dir The directory to be searched
     * @throws IllegalArgumentException If directory is null
     */
    public void search(Dir dir){
        if (dir == null)
            throw new IllegalArgumentException();

        System.out.println(toString());
        int count = 0;
        int totalSize = 0;
        for (FileObject fileObject: dir.getFileObject()){
            if (fitCri(fileObject)) {
                System.out.println(fileObject.toString());
                totalSize += fileObject.getSize();
                count++;
            }
        }

        System.out.println("The total number of files is " + count + ".");
        System.out.println("The total size of files is " + totalSize + " bytes.");
    }

    /**
     * Search the directory recursively to print the files that meet this criterion.
     * @param dir The directory to be searched
     * @param result The array to store the total number of files in index 0
     *               and the total size of files that meet the criterion in index 1
     * @param path The path from working directory to the file
     * @throws IllegalArgumentException If directory is null and the array store not 0 in both index 0 and 1
     */
    public void rSearch(Dir dir, int[] result, String path){
        if (dir == null)
            throw new IllegalArgumentException();

        for (FileObject fileObject: dir.getFileObject()){
            fileObject.printRSearch(this,result,path);
        }
    }

    /**
     * Check whether a file meets this criterion or not.
     * @param fileObject The file to be checked
     * @return boolean <code>true</code> if the file meet the criterion or <code>false</code> otherwise
     */
    public abstract boolean fitCri(FileObject fileObject);

    /**
     * Get the name of this criterion.
     * @return String of the name of this criterion
     */
    public String getName(){return name;}
    public abstract String toString();
}













