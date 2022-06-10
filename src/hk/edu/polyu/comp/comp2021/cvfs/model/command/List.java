package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;

/** Command "list" represents this command can list all document and directory in working directory. */
public class List implements Command{
    private Dir dir;

    /**
     * @param dir the directory to be listed
     */
    public List(Dir dir){
        this.dir = dir;
    }

    @Override
    public void execute() {
        dir.list();
    }


}
