package hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject;

import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cri;

/**
 * The class which implements FileObject interface indicates that this object can be created
 * in a directory.
 */
public interface FileObject {
    /**
     * Get the size of this file.
     * @return the size of file
     */
    int getSize();

    /**
     * Get the name of this file.
     * @return The name of file
     */
    String getName();

    /**
     * Set the name of FileObject
     * @param name The name to be set
     */
    void setName(String name);

    /**
     * List all the files contained in the working directory recursively
     * @param indent level of indentation
     * @param result a integer list storing the total number and size of files listed
     */
    void printRList(int indent, int[] result);

    /**
     * List all the contained in the working directory that satisfy the criterion
     * @param cri the criterion
     * @param result store the total number and size of files listed
     * @param path store the path to be printed
     */
    void printRSearch(Cri cri, int[] result, String path);
}
