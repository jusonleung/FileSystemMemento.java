package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cris;
import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.NegCri;

/** Command "newNegation" represents this command can create a new negation criterion of an existing criterion in file system.*/
public class NewNegation implements Command {
    private Cris cris;
    private String criName1;
    private String criName2;

    /**
     * @param cris the storage to be added
     * @param criName1 the name of new criterion in the storage
     * @param criName2 the name of exist criterion in the storage
     */
    public NewNegation(Cris cris, String criName1, String criName2){
        this.cris = cris;
        this.criName1 = criName1;
        this.criName2 = criName2;
    }

    @Override
    public void execute() {
        int target = cris.searchCri(criName2);
        cris.addCri(new NegCri(criName1,cris.getCri(target)));
    }
}
