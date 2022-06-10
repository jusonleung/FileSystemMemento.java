package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;

/** Command "delete" represents this command can delete a document or directory in working directory. */
public class Delete implements Command{
    private Dir dir;
    private String fileName;

    /**
     * @param dir the directory to delete a file
     * @param name the name of the file
     */
    public Delete(Dir dir, String name){
        this.dir = dir;
        this.fileName = name;
    }

    @Override
    public void execute() {
        dir.delete(fileName);
    }


}
