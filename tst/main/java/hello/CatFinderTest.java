package hello;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class CatFinderTest{

    // can be refactored to ensure the NPE is being thrown by line 37 in CatFinder and not elsewhere
    @Test(expected = NullPointerException.class)
    public void testEmptyInputVideoFrame() {
        CatFinder testCatFinder = new CatFinder([]);
    }

    // can be refactored to ensure the NPE is being thrown by line 60 in CatFinder and not elsewhere
    @Test(expected = NullPointerException.class)
    public void testEmptyInputVideoFrame() {
        CatFinder testCatFinder = new CatFinder([],100);
    }

    // can be refactored to ensure the NPE is being thrown by line 64 in CatFinder and not elsewhere
    @Test(expected = NullPointerException.class)
    public void testEmptyInputMatchThreshold() {
        CatFinder testCatFinder = new CatFinder(["testetsttetstst"],0);
    }

    // can be refactored to ensure the NPE is being thrown by line 64 in CatFinder and not elsewhere
    @Test(expected = NullPointerException.class)
    public void testEmptyInputMatchThreshold() {
        CatFinder testCatFinder = new CatFinder(["testetsttetstst"],null);
    }

    @Test
    public void testPerfectCatImage(){
        String[] testCat = new String[] {
                "+             +",
                "+++         +++",
                " +++++++++++++ ",
                " ++         ++ ",
                "++  +     +  ++",
                "++ +++   +++ ++",
                "++           ++",
                " ++   +++   ++ ",
                " ++         ++ ",
                "  ++ +   + ++  ",
                "  ++  +++  ++  ",
                "   ++     ++   ",
                "     +++++     ",
                "               ",
                "               ",
                "               "
        };

        CatFinder testCatFinder = new CatFinder(testCat);
        assertEquals(testCatFinder.findCats == 1);
    }

    @Test
    public void testNonPerfectCatImage(){
        String[] testCat = new String[] {
                "+             +",
                "+++++++++++++++",
                " +++++++++++++ ",
                " ++         ++ ",
                "++  +     +  ++",
                "++ +++   +++ ++",
                "++           ++",
                " ++   +++   ++ ",
                " ++         ++ ",
                "               ",
                "  ++  +++  ++  ",
                "   ++     ++   ",
                "               ",
                "               ",
                "               ",
                "               "
        };

        CatFinder testCatFinder = new CatFinder(testCat);
        assertEquals(testCatFinder.findCats == 1);
    }

    @Test
    public void testNotEnoughRowsImage(){
        String[] testCat = new String[] {
                "+             +",
                "+++++++++++++++",
                " +++++++++++++ ",
                " ++         ++ ",
                "++  +     +  ++",
                "++ +++   +++ ++",
                "++           ++",
                " ++   +++   ++ ",
                " ++         ++ ",
                "               ",
                "  ++  +++  ++  ",
                "   ++     ++   ",
                "               "
        };

        CatFinder testCatFinder = new CatFinder(testCat);
        assertEquals(testCatFinder.findCats == 0);
    }

    @Test
    public void testNotEnoughColumnsImage(){
        String[] testCat = new String[] {
                "+             ",
                "++++++++++++++",
                " +++++++++++++",
                " ++         ++",
                "++  +     +  +",
                "++ +++   +++ +",
                "++           +",
                " ++   +++   ++",
                " ++         ++",
                "              ",
                "  ++  +++  ++ ",
                "   ++     ++  ",
                "              ",
                "              ",
                "              ",
                "              "
        };

        CatFinder testCatFinder = new CatFinder(testCat);
        assertEquals(testCatFinder.findCats == 0);
    }

    @Test
    public void testBiggerThanReferenceCatImage(){
        String[] testCat = new String[] {
                "+             +@",
                "+++         +++@",
                " +++++++++++++ @",
                " ++         ++ @",
                "++  +     +  ++@",
                "++ +++   +++ ++@",
                "++           ++@",
                " ++   +++   ++ @",
                " ++         ++ @",
                "  ++ +   + ++  @",
                "  ++  +++  ++  @",
                "   ++     ++   @",
                "     +++++     @",
                "               @",
                "               @",
                "               @",
                "               @",
                "               @",
                "               @",
                "               @",
                "               @",
                "               @",
                "               @",
                "               @",
                "               @",
                "               @"
        };

        CatFinder testCatFinder = new CatFinder(testCat);
        assertEquals(testCatFinder.findCats == 1);
    }
}