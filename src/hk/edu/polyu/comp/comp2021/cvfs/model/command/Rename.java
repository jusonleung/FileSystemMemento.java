package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;

/** Command "rename" represents this command can rename a document or directory in working directory. */
public class Rename implements Command {
    private Dir dir;
    private String oldName;
    private String newName;

    /**
     * @param dir the working directory to rename a file
     * @param oldName the old name
     * @param newName the new name
     */
    public Rename(Dir dir, String oldName, String newName){
        this.dir = dir;
        this.oldName = oldName;
        this.newName = newName;
    }

    @Override
    public void execute() {
        dir.rename(oldName, newName);
    }

}
