package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;

/** Command "rList" represents this command can list all document and directory in working directory recursively.*/
public class RList implements Command{
    private Dir dir;

    /**
     * @param dir the directory to be list recursively
     */
    public RList(Dir dir){
        this.dir = dir;
    }

    @Override
    public void execute() {
        int[] result = new int[2];
        dir.rlist(dir, 0, result);

        System.out.println("The total number of files is " + result[0] + ".");
        System.out.println("The total size of files is " + result[1] + " bytes.");
    }


}
