package hk.edu.polyu.comp.comp2021.cvfs.model;

/**
 * Enumeration of Command
 */
public enum CmdType {
    /** Command "newDisk" represents this command can create a new disk in file system. */
    newDisk(true),
    /** Command "newDoc"  represents this command can create a new document in working directory. */
    newDoc(true),
    /** Command "newDir" represents this command can create a new directory in working directory. */
    newDir(true),
    /** Command changeDir represents this command can change the working directory of the file system. */
    changeDir(true),
    /** Command "rename" represents this command can rename a document or directory in working directory. */
    rename(true),
    /** Command "delete" represents this command can delete a document or directory in working directory. */
    delete(true),
    /** Command "list" represents this command can list all document and directory in working directory. */
    list(false),
    /** Command "rList" represents this command can list all document and directory in working directory recursively.*/
    rList(false),
    /** Command "newSimpleCri" represents this command can create new simple criterion in file system.*/
    newSimpleCri(true),
    /** Command "newNegation" represents this command can create a new negation criterion of an existing criterion in file system.*/
    newNegation(true),
    /** Command "newBinaryCri" represents this command can create a new binary criterion of two existing criterion in file system.*/
    newBinaryCri(true),
    /** Command "printAllCriteria" represents this command can list all criteria in file system. */
    printAllCriteria(false),
    /** Command "search" represents this command can search the document and directory which meet the criterion in working directory. */
    search(false),
    /** Command "rSearch" represents this command can search the document and directory which meet the criterion in working directory recursively*/
    rSearch(false),
    /** Command "store" represents this command can store this file system in a file. */
    store(false),
    /** Command "load" represents this command can load a file system to the CVFS. */
    load(true),
    /** Command "undo" represents this command can undo the command that changes the file system. */
    undo(false),
    /** Command "redo" represents this command can redo the command that changes the file system. */
    redo(false),
    /** Command "stop" represents this command can stop the CVFS. */
    stop(false);

    /**
     * Indicate whether this command create a memo or not based on whether this command changes the file system or not.
     */
    private boolean needMemo;

    CmdType(boolean needMemo){
        this.needMemo = needMemo;
    }

    /**
     * @return whether this command create a memo or not
     */
    public boolean getNeedMemo(){
        return needMemo;
    }


}

