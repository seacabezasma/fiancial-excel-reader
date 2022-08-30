package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CellUtils {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public static Date extractDateFromStringCell( String date ) {
        try {
            return formatter.parse( date );
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    public static String trimNumericString( String untreatedNumericString ){
        return untreatedNumericString.replace( "-", "" ).replace( "$", "" ).replace( ",", "" );
    }
}
