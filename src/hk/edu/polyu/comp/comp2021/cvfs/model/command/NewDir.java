package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.FileSystem;
import hk.edu.polyu.comp.comp2021.cvfs.model.WorkingDir;

/** Command "newDir" represents this command can create a new directory in working directory. */
public class NewDir implements Command{
    private FileSystem fileSystem;
    private String name;

    /**
     * @param workingDir the working directory to be added
     * @param name the name of the new directory
     */
    public NewDir(FileSystem fileSystem, String name){
        this.fileSystem = fileSystem;
        this.name = name;
    }

    @Override
    public void execute() {
        fileSystem.addDir(name);
    }

}
