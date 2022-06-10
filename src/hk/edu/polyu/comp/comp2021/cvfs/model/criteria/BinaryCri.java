package hk.edu.polyu.comp.comp2021.cvfs.model.criteria;


import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.FileObject;

/**
 * Binary criterion represents a criterion that satisfy both two or either of
 * two different criterion.
 */
public class BinaryCri extends Cri{
    private final Cri[] cris = new Cri[2];
    private final String logicOp;

    /**
     * Create a Binary criterion by two other criterion.
     * @param name the name of new Binary criterion
     * @param cri1 an criterion 1
     * @param logicOp a logical operator, either && (and) or || (or)
     * @param cri2 an criterion 2
     * @throws IllegalArgumentException If cri1 and cri2 is the same criterion
     */
    public BinaryCri(String name, Cri cri1, String logicOp, Cri cri2){
        super(name);
        if (cri1.equals(cri2))
            throw new IllegalArgumentException();

        cris[0] = cri1;
        cris[1] = cri2;
        this.logicOp = logicOp;
    }

    public String toString() {
        return "(" + cris[0].toString() + " " + logicOp + " " + cris[1].toString() + ")";
    }

    @Override
    public boolean fitCri(FileObject fileObject){
        boolean result = false;
        if(logicOp.equals("||")){
            result = cris[0].fitCri(fileObject) || cris[1].fitCri(fileObject);
        }
        if (logicOp.equals("&&")){
            result = cris[0].fitCri(fileObject) && cris[1].fitCri(fileObject);
        }
        return result;
    }
}
