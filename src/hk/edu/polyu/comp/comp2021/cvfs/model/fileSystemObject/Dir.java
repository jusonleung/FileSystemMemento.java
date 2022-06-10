package hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject;


import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cri;

import java.util.ArrayList;

/**
 * A directory
 */
public class Dir implements FileObject, java.io.Serializable {
    private String name;
    private final ArrayList<FileObject> fileObjects;

    /**
     * Initialize a directory
     * @param name directory's name
     */
    public Dir(String name){
        fileObjects = new ArrayList<>();
        this.name = name;
    }

    /**
     * Add a file in a directory
     * @param fileObject a file to be added
     * @throws IllegalArgumentException if fileObject is null
     */
    public void add(FileObject fileObject){
        if (fileObject == null)
            throw new IllegalArgumentException();
        if (searchName(fileObject.getName()) != -1){
            System.out.println("Repeated name");
            throw new IllegalArgumentException();
        }

        fileObjects.add(fileObject);
    }

    /**
     * Search the index of a file in this directory
     * @param name a name to be search
     * @return the index of file found or -1 if the file not found
     * @throws IllegalArgumentException if name is null
     */
    public int searchName(String name){
        if (name == null)
            throw new IllegalArgumentException();

        for (FileObject fileObject:fileObjects){
            if (fileObject.getName().equals(name)){
                return fileObjects.indexOf(fileObject);
            }
        }
        return -1;
    }

    /**
     * Delete the a file in this directory
     * @param name a name to be delete
     * @throws IllegalArgumentException if name is null or file not found
     */
    public void delete(String name){
        int docIndex = searchName(name);
        if (docIndex == -1 || name == null)
            throw new IllegalArgumentException();

        fileObjects.remove(docIndex);
    }

    /**
     * Rename the a file in this directory
     * @param oldName name of file that to be rename
     * @param newName the file new name
     * @throws IllegalArgumentException if oldName or newName is null or file not found
     */
    public void rename(String oldName, String newName){
        int tIndex = searchName(oldName);
        if (tIndex == -1 || oldName == null || newName == null)
            throw new IllegalArgumentException();

        fileObjects.get(tIndex).setName(newName);
    }

    /**
     * List all the document and directory directly contained in this directory
     */
    public void list(){
        int count = 0;
        int totalSize = 0;

        for (FileObject i:fileObjects){
            System.out.println(i.toString() + " " + i.getSize() + " bytes");
            count++;
            totalSize += i.getSize();
        }
        System.out.println("The total number of files is " + count);
        System.out.println("The total size of files is " + totalSize + " bytes");
    }

    /**
     * List all the document and directory contained in this directory recursively
     * @param dir this directory
     * @param indent level of indentation
     * @param result a list storing the total number and size of files listed
     */
    public void rlist(Dir dir, int indent, int[] result){

        for (FileObject i:dir.getFileObject()){
            i.printRList(indent, result);
        }

    }

    /**
     * Add indentation to the string to be print out
     * @param string string to be print
     * @param indent level of indentation
     * @return print out result
     */
    private String addIndent(String string, int indent){
        StringBuilder result = new StringBuilder();
        result.append(string);
        for (int j = 0; j < indent; j++){
            result.insert(0,"|\t");
        }
        return result.toString();
    }

    /**
     * @return ArrayList of file in directory
     */
    public ArrayList<FileObject> getFileObject(){
        return fileObjects;
    }

    /**
     * Get a file from directory
     * @param index the index of file in ArrayList
     * @return a file
     */
    public FileObject getFileObject(int index){
        return fileObjects.get(index);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void printRList(int indent, int[] result){
        System.out.println(addIndent(toString() + " " + getSize() + " bytes",indent));
        result[0] ++;
        result[1] += InitSize.DIR.getSize();
        rlist(this, indent+1, result);
    }

    @Override
    public void printRSearch(Cri cri, int[] result, String path) {
        if(cri.fitCri(this)){
            System.out.println(path + ":" + toString() + " " + getSize() + " bytes");
            result[0]++;
            result[1]+= getSize();
        }
        path += ":" + toString();
        cri.rSearch(this, result, path);
    }

    @Override
    public int getSize(){
        int totalSize = InitSize.DIR.getSize();
        for (FileObject i:fileObjects){
            totalSize += i.getSize();
        }
        return totalSize;
    }

    @Override
    public String getName(){return name;}
    @Override
    public void setName(String name){this.name = name;}


}

