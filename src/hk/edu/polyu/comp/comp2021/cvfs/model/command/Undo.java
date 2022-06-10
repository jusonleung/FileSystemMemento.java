package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.FileSystem;
import hk.edu.polyu.comp.comp2021.cvfs.model.Undoer;

/** Command "undo" represents this command can undo the command that changes the file system. */
public class Undo implements Command {
    private FileSystem fileSystem;
    private Undoer undoer;

    /**
     * @param fileSystem the fileSystem to be undo
     * @param undoer the machine to get images of the filesystem
     */
    public Undo(FileSystem fileSystem, Undoer undoer){
        this.fileSystem = fileSystem;
        this.undoer = undoer;
    }

    @Override
    public void execute() {
        fileSystem.setMemento(undoer.undo());
    }

}
