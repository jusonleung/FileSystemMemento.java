package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.FileSystem;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.Doc;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.DocType;
import hk.edu.polyu.comp.comp2021.cvfs.model.WorkingDir;

/** Command "newDoc"  represents this command can create a new document in working directory. */
public class NewDoc implements Command{
    private FileSystem fileSystem;
    private String docName;
    private DocType docType;
    private String content;

    /**
     * @param docName the name of the new document
     * @param docType the type of the new document
     * @param content the content of the new document
     */
    public NewDoc(FileSystem fileSystem, String docName, DocType docType, String content){
        this.fileSystem = fileSystem;
        this.docName = docName;
        this.docType = docType;
        this.content = content;
    }

    @Override
    public void execute() {
        fileSystem.addDoc(new Doc(docName,docType,content));
    }
}
