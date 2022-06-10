package hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject;

import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cri;

import java.io.Serializable;

/**
 * A document
 */
public class Doc implements FileObject, Serializable {

    private String content;
    private String name;
    private final DocType type;
    private int size;

    /**
     * Creating new document
     * @param name new document's name
     * @param type new document's type
     * @param content new document's content
     */
    public Doc(String name, DocType type, String content){
        this.name = name;
        this.type = type;
        this.content = content;
        this.size += InitSize.DOC.getSize() + content.length() * 2 ;
    }

    public String toString(){
        return name + "." + type.toString().toLowerCase();
    }

    @Override
    public void printRList(int indent, int[] result) {
        System.out.println(addIndent(toString() + " " + size + " bytes",indent));
        result[0]++;
        result[1] += size;
    }

    private String addIndent(String string, int indent){
        StringBuilder result = new StringBuilder();
        result.append(string);
        for (int j = 0; j < indent; j++){
            result.insert(0,"|\t");
        }
        return result.toString();
    }

    @Override
    public void printRSearch(Cri cri, int[] result, String path) {
        if(cri.fitCri(this)){
            System.out.println(path + ":" + toString() + " " + getSize() + " bytes");
            result[0]++;
            result[1]+= getSize();
        }
    }

    @Override
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String getName(){return name;}
    @Override
    public int getSize(){return size;}

    /**
     * @return the document's type
     */
    public DocType getType(){return type;}
}
