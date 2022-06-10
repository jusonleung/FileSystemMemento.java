package hk.edu.polyu.comp.comp2021.cvfs.model.criteria;

import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Doc;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.FileObject;

/**
 * IsDocument class is a criterion that selects the files that is a document and
 * is a default criterion in class Cris.
 */
public class IsDocument extends Cri{

    /**
     * Create a criterion name "IsDocument".
     */
    public IsDocument(){
        super("IsDocument");
    }

    public String toString() {
        return "IsDocument";
    }

    @Override
    public boolean fitCri(FileObject fileObject){
        if (fileObject == null)
            throw new IllegalArgumentException();

        return fileObject instanceof Doc;
    }
}
