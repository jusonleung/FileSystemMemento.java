package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cris;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;

/**
 * Command "rSearch" represents this command can search the document and directory which meet the criterion in
 * working directory recursively
 */
public class RSearch implements Command{
    private Dir dir;
    private Cris cris;
    private String criName;

    /**
     * @param dir the directory to be searched
     * @param cris the storage of the target criterion
     * @param criName the name of the target criterion
     */
    public RSearch(Dir dir, Cris cris, String criName){
        this.dir = dir;
        this.cris = cris;
        this.criName = criName;
    }

    @Override
    public void execute() {
        int target4 = cris.searchCri(criName);
        int[] result = new int[2];
        String path = "$";

        System.out.println(cris.getCri(target4).toString());
        cris.getCri(target4).rSearch(dir,result,path);
        System.out.println("The total number of files is " + result[0] + ".");
        System.out.println("The total size of files is " + result[1] + " bytes.");
    }

    @Override
    public String toString() {
        return "RSearch{" +
                "dir=" + dir +
                ", cris=" + cris +
                ", criName='" + criName + '\'' +
                '}';
    }
}
