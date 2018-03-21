package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatFinder {

    private final Logger logger = LoggerFactory.getLogger(CatFinder.class);

    private int matchingRate;
    private char[][] contentCharArray;
    private int contentWidth;
    private int contentLength;

    private final char[][] cat = new char[][] {
        "+             +".toCharArray(),
        "+++         +++".toCharArray(),
        " +++++++++++++ ".toCharArray(),
        " ++         ++ ".toCharArray(),
        "++  +     +  ++".toCharArray(),
        "++ +++   +++ ++".toCharArray(),
        "++           ++".toCharArray(),
        " ++   +++   ++ ".toCharArray(),
        " ++         ++ ".toCharArray(),
        "  ++ +   + ++  ".toCharArray(),
        "  ++  +++  ++  ".toCharArray(),
        "   ++     ++   ".toCharArray(),
        "     +++++     ".toCharArray(),
        "               ".toCharArray(),
        "               ".toCharArray(),
        "               ".toCharArray()
    };

    public CatFinder(String[] content) throws NullPointerException {
        if(content == null){
            logger.error("Input videoframe is NULL");
            throw new NullPointerException("Input videoframe is NULL");
        }

        this.contentLength = content.length;
        this.matchingRate = 85;
        if(this.contentLength != 0){
            this.contentWidth = content[0].length();
        }

        this.contentCharArray = new char[this.contentLength][];

        for (int i = 0; i < content.length; i++) {
            this.contentCharArray[i] = content[i].toCharArray();
        }
        logger.debug("Successfully initialized CatFinder object");
    }


    public CatFinder(String[] content, int matchThreshold) throws NullPointerException {

        // null checks
        if(content == null){
            logger.error("Input videoframe is NULL");
            throw new NullPointerException("Input videoframe is NULL");
        }
        if(matchThreshold == 0){
            logger.error("Match Threshold is NULL");
            throw new NullPointerException("Match Threshold is NULL");
        }

        // store for later processing
        this.contentLength = content.length;
        this.matchingRate = matchThreshold;
        if(this.contentLength != 0){
            this.contentWidth = content[0].length();
        }

        // create 2D char array to ease access for processing
        this.contentCharArray = new char[this.contentLength][];
        for (int i = 0; i < content.length; i++) {
            this.contentCharArray[i] = content[i].toCharArray();
        }
        logger.debug("Successfully initialized CatFinder object");
    }

    public int findCats() {

        // check if incoming videoframe is of minimum size to hold a reference cat
        if(this.contentCharArray.length < 16 || this.contentCharArray[0].length < 15){
            return 0;
        }

        // start iterating to find cats by traversing input text matrix by columns and then by rows
        int foundcats = 0;
        for(int rowIndex = 0; rowIndex < this.contentLength; rowIndex++){
            for(int columnIndex = 0; columnIndex < this.contentWidth; columnIndex++){
                if(findCatWithinFrame(rowIndex, columnIndex)){
                    foundcats++;
                }
            }
        }
        logger.debug("Successfully found cats in input");
        return foundcats;
    }

    private boolean findCatWithinFrame(int inputY, int inputX){

        // use this to store characters that have matched to determine if incoming image matches reference
        int matchingChars = 0;

        for(int rowIndex = 0; rowIndex < 16; rowIndex++){
            for(int columnIndex = 0; columnIndex < 15; columnIndex++ ){

                // if we're checking an out of bounds index, exit by returning false
                if(rowIndex+inputY >= contentLength || columnIndex+inputX >= contentWidth){
                    logger.debug("Ran out of bounds");
                    return false;
                }

                if(this.contentCharArray[rowIndex + inputY][columnIndex + inputX] == this.cat[rowIndex][columnIndex]){
                    matchingChars++;
                }
            }
        }

        // utilize the match threshold to determine if this is a match and return
        return (100 * matchingChars) / 240 >= matchingRate;
    }
}