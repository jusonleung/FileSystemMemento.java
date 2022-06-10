package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.BinaryCri;
import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cris;

/** Command "newBinaryCri" represents this command can create a new binary criterion of two existing criterion in file system.*/
public class NewBinaryCri implements Command{
    private Cris cris;
    private String criName1;
    private String criName3;
    private String logicOp;
    private String criName4;

    /**
     * @param cris storage to be added
     * @param criName1 the new criterion name
     * @param criName3 the criterion name in the storage
     * @param logicOp the logical operator to join two criteria
     * @param criName4 the another criterion name in the storage
     */
    public NewBinaryCri(Cris cris, String criName1, String criName3, String logicOp, String criName4){
        this.cris = cris;
        this.criName1 = criName1;
        this.criName3 = criName3;
        this.logicOp = logicOp;
        this.criName4 = criName4;
    }

    @Override
    public void execute() {
        int target = cris.searchCri(criName3);
        int target2 = cris.searchCri(criName4);
        cris.addCri(new BinaryCri(criName1,cris.getCri(target),logicOp,cris.getCri(target2)));
    }

}
