package hk.edu.polyu.comp.comp2021.cvfs.model.criteria;


import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.FileObject;

/**
 * NegCri class is a criterion that is the negation of an criterion
 */
public class NegCri extends Cri{
    private final Cri cri;

    /**
     * Create a negation of an criterion
     * @param name the name of created negation criterion
     * @param cri an existing criterion
     */
    public NegCri(String name, Cri cri){
        super(name);
        if (cri == null)
            throw new IllegalArgumentException();

        this.cri = cri;
    }

    @Override
    public boolean fitCri(FileObject fileObject) {
        return !cri.fitCri(fileObject);
    }

    public String toString(){
        return "!(" + cri.toString() + ")";
    }
}
