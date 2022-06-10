package hk.edu.polyu.comp.comp2021.cvfs.model.criteria;

import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Doc;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.DocType;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.FileObject;

/**
 * TypeSimpleCri class is a simple criterion that selects the files with
 * a specific document type.
 */
public class TypeSimpleCri extends Cri {
    private final DocType val;

    /**
     * Create a new simple criterion with document type attribute
     * @param name criterion's name
     * @param val criterion's value
     */
    public TypeSimpleCri(String name, DocType val){
        super(name);
        if (val == null)
            throw new IllegalArgumentException();

        this.val = val;
    }

    @Override
    public boolean fitCri(FileObject fileObject) {
        if (!(fileObject instanceof Doc)) return false;
        Doc doc = (Doc) fileObject;
        return doc.getType() == val;
    }

    public String toString(){
        return "type equals \"" + val.toString().toLowerCase() + "\"";
    }

}
