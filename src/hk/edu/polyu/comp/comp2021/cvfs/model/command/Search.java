package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cris;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Dir;

/** Command "search" represents this command can search the document and directory which meet the criterion in working directory. */
public class Search implements Command {
    private Dir dir;
    private Cris cris;
    private String criName;

    /**
     * @param dir the directory to be searched
     * @param cris the storage of the target criterion
     * @param criName the name of the target criterion
     */
    public Search(Dir dir, Cris cris, String criName){
        this.dir = dir;
        this.cris = cris;
        this.criName = criName;
    }

    @Override
    public void execute() {
        int target3 = cris.searchCri(criName);
        cris.getCri(target3).search(dir);
    }

}
