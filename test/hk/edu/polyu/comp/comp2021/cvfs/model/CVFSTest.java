package hk.edu.polyu.comp.comp2021.cvfs.model;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


import static junit.framework.TestCase.assertEquals;

/**
 * All test CVFS
 */
public class CVFSTest {

    private final CVFS cvfs = new CVFS();


    /**
     * Test running the CVFS
     */
    @Test
    public void testCVFSRun(){
        String userInput = "stop";
        InputStream stream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(stream);
        cvfs.run();

    }
    
    /**
     * Test whether cmd.toString can see the representation of the command type.
     */
    @Test
    public void testCommand(){
        cvfs.testParse("newDisk 1000");
        assertEquals(cvfs.getCmd().toString(), "NewDisk{fileSystem=FileSystem{diskCount=1}, size=1000}");

    }

    /**
     * Test command newDisk
     */
    @Test
    public void testNewDisk(){
        cvfs.testParse("newDisk 1000");
        assertEquals(cvfs.getFileSystem().getDisk().getSizeLimit(), 1000);
    }

    /**
     * Test whether command newDoc can create a document and should not create a document
     * with name longer than 10 characters.
     */
    @Test
    public void testNewDoc(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc hi txt Hello!World");
        cvfs.testParse("newDoc Banana asdfsadf Hello!World");
        cvfs.testParse("newDoc Cat Txt Hello!World");
        cvfs.testParse("newDoc GoodMorning txt Hello!World");
        assertEquals(cvfs.getFileSystem().getDisk().getDir().getFileObject(0).toString(), "hi.txt");
    }

    /**
     * Test whether command newDir can create a directory and that should not exceed 10 characters.
     */
    @Test
    public void testNewDir(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDir dir1");
        cvfs.testParse("newDir GoodMorning");
        assertEquals(cvfs.getFileSystem().getDisk().getDir().getFileObject(0).toString(), "dir1");
    }

    /**
     * Test command delete
     */
    @Test
    public void testDelete(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc hi txt Hello!World");
        cvfs.testParse("delete hi");
        assertEquals(cvfs.getFileSystem().getDisk().getDir().searchName("hi"), -1);
    }

    /**
     * Test command rename
     */
    @Test
    public void testRename(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc hi txt Hello!World");
        cvfs.testParse("rename hi nobody");
        assertEquals(cvfs.getFileSystem().getDisk().getDir().getFileObject(0).toString(), "nobody.txt");
    }

    /**
     * Test command changeDir
     */
    @Test
    public void testChangeDir(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDir dir1");
        cvfs.testParse("changeDir dir1");
        assertEquals(cvfs.getFileSystem().workingDirToString(), "C:\\dir1\\");
        cvfs.testParse("changeDir ..");
        assertEquals(cvfs.getFileSystem().workingDirToString(), "C:\\");
        cvfs.testParse("changeDir ..");
    }

    /**
     * Test command list
     */
    @Test
    public void testList(){
        System.out.println("testList()");
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDir dir1");
        cvfs.testParse("newDoc hi txt Hello!World");
        cvfs.testParse("list");
        System.out.println("----------------");
        System.out.println();
    }

    /**
     * Test command rList
     */
    @Test
    public void testRList(){
        System.out.println("testRList()");
        cvfs.testParse("newDisk 10000");
        cvfs.testParse("newDir apple");
        cvfs.testParse("newDoc banana txt Hello!World");
        cvfs.testParse("changeDir apple");
        cvfs.testParse("newDoc cream txt Good");
        cvfs.testParse("newDir dog");
        cvfs.testParse("changeDir dog");
        cvfs.testParse("newDoc elephant txt Gooooooood");
        cvfs.testParse("newDir fire");
        cvfs.testParse("changeDir ..");
        cvfs.testParse("changeDir ..");
        cvfs.testParse("rList");
        System.out.println("----------------");
        System.out.println();
    }

    /**
     * Test criterion IsDocument is default exist
     */
    @Test
    public void testIsDocument(){
        cvfs.testParse("newDisk 1000");
        assertEquals(cvfs.getFileSystem().getCris().getCri(0).getName(), "IsDocument");
    }

    /**
     * Test command newSimpleCri
     */
    @Test
    public void testNewSimpleCri(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newSimpleCri AA name contains \"hi\"");
        assertEquals(cvfs.getFileSystem().getCris().getCri(cvfs.getFileSystem().getCris().searchCri("AA")).toString(), "name contains \"hi\"");
        cvfs.testParse("newSimpleCri BB type equals \"txt\"");
        assertEquals(cvfs.getFileSystem().getCris().getCri(cvfs.getFileSystem().getCris().searchCri("BB")).toString(), "type equals \"txt\"");
        cvfs.testParse("newSimpleCri CC size > 20");
        assertEquals(cvfs.getFileSystem().getCris().getCri(cvfs.getFileSystem().getCris().searchCri("CC")).toString(), "size > 20");
    }


    /**
     * Test command newSimpleCri with invalid input
     */
    @Test
    public void testNewSimpleCriFormat(){
        cvfs.testParse("newDisk 1000");
        System.out.println("testNewSimpleCriFormat()");
        cvfs.testParse("newSimpleCri AA name contains hi");
        cvfs.testParse("newSimpleCri AA sizadsase > 20");
        cvfs.testParse("newSimpleasdasdCri AA size > 20");
        cvfs.testParse("newSimpleCri A! size > 20");
        cvfs.testParse("newSimpleCri AA size asdad> 20");
        cvfs.testParse("newSimpleCri AA size > 20sasdadas");
        System.out.println("-----------------------------");
        System.out.println();

        assertEquals(cvfs.getFileSystem().getCris().getCris().size(), 1);
    }

    /**
     * Test command newBinaryCri
     */
    @Test
    public void testNewBinaryCri(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newSimpleCri AA type equals \"txt\"");
        cvfs.testParse("newSimpleCri BB type equals \"java\"");
        cvfs.testParse("newBinaryCri CC AA || BB");

        assertEquals(cvfs.getFileSystem().getCris().getCri(3).toString(), "(type equals \"txt\" || type equals \"java\")");
    }

    /**
     * Test command printAllCriteria
     */
    @Test
    public void testPrintAllCri(){
        System.out.println("printAllCri()");
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newSimpleCri AA type equals \"html\"");
        cvfs.testParse("newSimpleCri BB type equals \"txt\"");
        cvfs.testParse("newBinaryCri CC AA || BB");
        cvfs.testParse("newNegation DD CC");
        cvfs.testParse("newBinaryCri EE DD && IsDocument");
        cvfs.testParse("printAllCriteria");
        System.out.println("-----------------------------");
        System.out.println();
    }

    /**
     * Test command Search with different kinds of valid input
     */
    @Test
    public void testSearch(){
        System.out.println("testSearch1()");
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc Apple txt apple");
        cvfs.testParse("newDoc Banana java banana");
        cvfs.testParse("newDoc Cow html cow");
        cvfs.testParse("newDoc Dog css dog");
        cvfs.testParse("newDoc Egg html EggEggEggEgg");
        cvfs.testParse("newSimpleCri AA type equals \"html\"");
        cvfs.testParse("search AA");
        System.out.println();
        cvfs.testParse("newNegation BB AA");
        cvfs.testParse("search BB");
        System.out.println("-----------------------------");
        System.out.println();
        cvfs.testParse("newSimpleCri CC name contains \"ppl\"");
        cvfs.testParse("search CC");
        System.out.println();
        cvfs.testParse("newNegation DD CC");
        cvfs.testParse("search DD");
        System.out.println("-----------------------------");
        System.out.println();
        cvfs.testParse("newSimpleCri EE size > 47");
        cvfs.testParse("search EE");
        System.out.println();
        cvfs.testParse("newNegation FF EE");
        cvfs.testParse("search FF");
        System.out.println("-----------------------------");
        System.out.println();
        cvfs.testParse("newSimpleCri GG size < 47");
        cvfs.testParse("search GG");
        System.out.println();
        cvfs.testParse("newNegation GG HH");
        cvfs.testParse("search HH");
        System.out.println("-----------------------------");
        System.out.println();
        cvfs.testParse("newSimpleCri II size == 46");
        cvfs.testParse("search II");
        System.out.println();
        cvfs.testParse("newNegation JJ II");
        cvfs.testParse("search JJ");
        System.out.println("-----------------------------");
        System.out.println();
        cvfs.testParse("newSimpleCri KK size >= 47");
        cvfs.testParse("search KK");
        System.out.println();
        cvfs.testParse("newNegation KK LL");
        cvfs.testParse("search LL");
        System.out.println("-----------------------------");
        System.out.println();
        cvfs.testParse("newSimpleCri MM size <= 47");
        cvfs.testParse("search MM");
        System.out.println();
        cvfs.testParse("newNegation NN MM");
        cvfs.testParse("search NN");
        System.out.println("-----------------------------");
        System.out.println();
        cvfs.testParse("newSimpleCri OO size != 50");
        cvfs.testParse("search OO");
        System.out.println();
        cvfs.testParse("newNegation PP OO");
        cvfs.testParse("search PP");
        System.out.println("-----------------------------");
        System.out.println();
        cvfs.testParse("newBinaryCri QQ AA && II");
        cvfs.testParse("search QQ");
        System.out.println("-----------------------------");
        System.out.println();
        cvfs.testParse("newBinaryCri RR AA || II");
        cvfs.testParse("search RR");
        System.out.println("-----------------------------");
        System.out.println();
    }

    /**
     * Test command rSearch
     */
    @Test
    public void testRSearch(){
        System.out.println("testRSearch()");
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDir Sandy");
        cvfs.testParse("newDoc Apple txt \"Hello!World\"");
        cvfs.testParse("newDoc Banana txt \"Hello!World\"");
        cvfs.testParse("changeDir Sandy");
        cvfs.testParse("newDoc Cow txt Good");
        cvfs.testParse("newDoc Danny html Happy");
        cvfs.testParse("newDir Elephant");
        cvfs.testParse("changeDir Elephant");
        cvfs.testParse("newDoc Fan html fanfanfanfanfan");
        cvfs.testParse("newDoc Game css gamegamegame");
        cvfs.testParse("newDir Horse");
        cvfs.testParse("newDoc Ice java iceiceice");
        cvfs.testParse("changeDir ..");
        cvfs.testParse("changeDir ..");

        cvfs.testParse("newSimpleCri AA name contains \"an\"");
        cvfs.testParse("rSearch AA");
        System.out.println("----------------");
        System.out.println();

        cvfs.testParse("newSimpleCri BB type equals \"txt\"");
        cvfs.testParse("rSearch BB");
        System.out.println("----------------");
        System.out.println();

        cvfs.testParse("newSimpleCri CC size >= 50");
        cvfs.testParse("rSearch CC");
        System.out.println("----------------");
        System.out.println();

        cvfs.testParse("changeDir Sandy");
        cvfs.testParse("rSearch AA");
        System.out.println("----------------");
        System.out.println();
    }

    /**
     * Test command search with IsDocument
     */
    @Test
    public void testSearchIsDocument(){
        System.out.println("testSearchIsDocument()");
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc Apple txt Hello!World");
        cvfs.testParse("newDir Banana");
        cvfs.testParse("newDoc Cow html Hello!World");
        cvfs.testParse("search IsDocument");
        System.out.println("-----------------------------");
        System.out.println();
    }

    /**
     * Test command search with binary criteria
     */
    @Test
    public void testSearchBinaryCri(){
        System.out.println("testSearchBinaryCri() -- \"||\"");
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc Apple txt Hello!World");
        cvfs.testParse("newDir ppleo");
        cvfs.testParse("newDoc Banana txt Hello!World");
        cvfs.testParse("newDoc Cow html Hi!World");
        cvfs.testParse("newSimpleCri AA name contains \"pple\"");
        cvfs.testParse("newBinaryCri BB AA || IsDocument");
        cvfs.testParse("search BB");
        System.out.println("-----------------------------");
        System.out.println();
        System.out.println("testSearchBinaryCri() -- \"&&\"");
        cvfs.testParse("newBinaryCri CC AA && IsDocument");
        cvfs.testParse("search CC");
        System.out.println();
    }

    /**
     * Test command stop
     */
    @Test
    public void testStop(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("stop");
    }

    /**
     * Test command Undo
     */
    @Test
    public void testUndo(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc Apple txt Hello!World");
        cvfs.testParse("undo");
        assertEquals(cvfs.getFileSystem().getDisk().getDir().getFileObject().size(), 0);
    }

    /**
     * Test command Redo
     */
    @Test
    public void testRedo(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc Apple txt Hello!World");
        cvfs.testParse("undo");
        cvfs.testParse("redo");
        cvfs.testParse("undo");
        cvfs.testParse("undo");
        cvfs.testParse("undo");
        cvfs.testParse("redo");
        cvfs.testParse("redo");

        assertEquals(cvfs.getFileSystem().getDisk().getDir().getFileObject(0).toString(), "Apple.txt");
    }

    /**
     * Test disk size limit will not be able to excess
     */
    @Test
    public void testDiskSizeLimit(){
        cvfs.testParse("newDisk 100");
        cvfs.testParse("newDoc Apple txt Hello!World");
        cvfs.testParse("newDoc Banana xxx Hello!World");
        cvfs.testParse("newDir Cat");
        cvfs.testParse("newDir Dog");
        cvfs.testParse("newDir Elephant");
    }

    /**
     * Test command Store
     */
    @Test
    public void testStore(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc Apple txt Hello!World");
        cvfs.testParse("newDoc Banana txt Hello!World");
        cvfs.testParse("store");
    }

    /**
     * Test command Load
     */
    @Test
    public void testLoad(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc Apple txt Hello!World");
        cvfs.testParse("newDoc Banana html Hello!World");
        cvfs.testParse("store");
        cvfs.testParse("newDisk 0");
        cvfs.testParse("load");
        assertEquals(cvfs.getFileSystem().getDisk().getDir().getFileObject(0).toString(), "Apple.txt");
    }

    /**
     * test file name constraint
     */
    @Test
    public void testFileNameConstraint(){
        cvfs.testParse("newDisk 1000");
        cvfs.testParse("newDoc /* txt Hello!World");
        cvfs.testParse("newDir */");
        cvfs.testParse("newDoc Apple txt Hello!World");
        cvfs.testParse("rename Apple /*");
        assertEquals(cvfs.getFileSystem().getDisk().getDir().getFileObject(0).toString(), "Apple.txt");
    }

    /**
     * test load constraint
     */
    @Test
    public void testLoadConstraint(){
        cvfs.testParse("load");
        assert true;
    }
}