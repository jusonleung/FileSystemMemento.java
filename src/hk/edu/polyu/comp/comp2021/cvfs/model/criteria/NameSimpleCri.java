package hk.edu.polyu.comp.comp2021.cvfs.model.criteria;

import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.FileObject;

/**
 * NameSimpleCri class is a criterion that selects all files their name contains a
 * certain string.
 */
public class NameSimpleCri extends Cri {
    private final String val;

    /**
     * Create a new simple criterion with name attribute.
     * @param name criterion's name
     * @param val criterion's value
     */
    public NameSimpleCri(String name, String val){
        super(name);
        if (val == null || !isValid(val))
            throw new IllegalArgumentException();

        this.val = val.substring(1,val.length()-1);
    }

    @Override
    public boolean fitCri(FileObject fileObject) {
        if (fileObject == null)
            throw new IllegalArgumentException();

        return fileObject.getName().contains(val);
    }

    /**
     * Check whether the simple criterion with name attribute is valid or not.
     * @param val criterion's value
     * @return <code>true</code> if valid, <code>false</code> if invalid
     * @throws IllegalArgumentException if value have no double quotation marks
     */
    private boolean isValid(String val){
        if (val.charAt(0) == '\"' && val.charAt(val.length()-1) == '\"'){
            return true;
        }else{
            System.out.print("Invalid value - ");
            throw new IllegalArgumentException();
        }
    }

    public String toString(){
        return "name contains" + " \"" + val + "\"";
    }
}