package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class CellUtils {

    public static Date extraxctDateFromStringCell( String date ) {
        try {
            return DateFormat.getDateTimeInstance().parse( date );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String trimNumericString( String untreatedNumericString ){
        return untreatedNumericString.replace( "-", "" ).replace( "$", "" );
    }
}
