package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.FileSystem;
import hk.edu.polyu.comp.comp2021.cvfs.model.WorkingDir;

/** Command changeDir represents this command can change the working directory of the file system. */
public class ChangeDir implements Command {
    private FileSystem fileSystem;
    private String dirName;


    /**
     * @param name name of working directory to be changed
     */
    public ChangeDir(FileSystem fileSystem, String name){
        this.fileSystem = fileSystem;
        this.dirName = name;
    }

    @Override
    public void execute() {
        if (dirName.equals("..")) {
            fileSystem.closeDir();
        } else {
            fileSystem.openDir(dirName);
        }
    }

}
