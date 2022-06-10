package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.Disk;
import hk.edu.polyu.comp.comp2021.cvfs.model.FileSystem;
import hk.edu.polyu.comp.comp2021.cvfs.model.WorkingDir;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;

/** Command "newDisk" represents this command can create a new disk in file system. */
public class NewDisk implements Command{
    private FileSystem fileSystem;
    private int size;

    /**
     * @param fileSystem the filesystem to be added
     * @param size the size limit of the new disk
     */
    public NewDisk(FileSystem fileSystem, int size){
        this.fileSystem = fileSystem;
        this.size = size;
    }

    @Override
    public void execute() {
        String defaultName = Character.toString((char)("C".codePointAt(0) + fileSystem.getDiskCount()));
        fileSystem.addDiskCount(1);
        fileSystem.setDisk(new Disk(size,defaultName));
        fileSystem.setwDir(fileSystem.getDisk().getDir());
    }

    @Override
    public String toString() {
        return "NewDisk{" +
                "fileSystem=" + fileSystem.toString() +
                ", size=" + size +
                '}';
    }
}
