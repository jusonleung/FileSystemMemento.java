package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.FileSystem;

/** Command "store" represents this command can store this file system in a file. */
public class Store implements Command{
    private FileSystem fileSystem;

    /**
     * @param fileSystem the filesystem to be stored
     */
    public Store(FileSystem fileSystem){
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        fileSystem.serialize("temp\\fileSystem.txt");
    }

}
