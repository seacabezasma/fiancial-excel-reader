package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static util.WorkbookConstants.DEFAULT_PATH;

public class WorkbookUtils {

    public static FileInputStream loadFile( String pathToFile, boolean createDefault ){
        FileInputStream inputStream;

        try {
            if ( createDefault ){
                inputStream = new FileInputStream( DEFAULT_PATH );
            } else {
                inputStream = new FileInputStream( pathToFile );
            }
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException( e );
        }
        return inputStream;
    }

    public static FileInputStream loadFile( String pathToFile ){
        return loadFile( pathToFile, false );
    }

    public static FileInputStream loadDefaultFile(){
        return loadFile( "", true );
    }
}
