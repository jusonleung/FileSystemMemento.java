package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cris;

/** Command "printAllCriteria" represents this command can list all criteria in file system. */
public class PrintAllCriteria implements Command{
    private Cris cris;

    /**
     * @param cris the storage to be printed
     */
    public PrintAllCriteria(Cris cris){
        this.cris = cris;
    }

    @Override
    public void execute() {
        cris.printAllCri();
    }
}
