package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;
import hk.edu.polyu.comp.comp2021.cvfs.model.FileSystem;

/** Command "load" represents this command can load a file system to the CVFS. */
public class Load implements Command{
    private CVFS cvfs;
    private FileSystem fileSystem;

    /**
     * @param cvfs the cvfs to change the filesystem
     * @param fileSystem the fileSystem to be covered
     */
    public Load(CVFS cvfs, FileSystem fileSystem){
        this.cvfs = cvfs;
        this.fileSystem = fileSystem;
    }
    @Override
    public void execute() {
        FileSystem temp =fileSystem.deserialize("temp\\fileSystem.txt");
        if (temp== null)
            throw new IllegalArgumentException();
        cvfs.setFileSystem(temp);
    }

}
