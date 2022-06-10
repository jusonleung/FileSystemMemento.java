package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.FileSystem;
import hk.edu.polyu.comp.comp2021.cvfs.model.Undoer;

/** Command "redo" represents this command can redo the command that changes the file system. */
public class Redo implements Command{
    private FileSystem fileSystem;
    private Undoer undoer;

    /**
     * @param fileSystem the filesystem to be redo
     * @param undoer the storage to get the image of the filesystem
     */
    public Redo(FileSystem fileSystem, Undoer undoer){
        this.fileSystem = fileSystem;
        this.undoer = undoer;
    }

    @Override
    public void execute() {
        fileSystem.setMemento(undoer.redo());
    }
}
