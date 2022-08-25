package factory;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.WorkbookUtils;

import java.io.IOException;

public class WorkbookProviderFactory {

    public static Workbook getWorkbookHandler( String pathToFile ) throws IOException {

        return new XSSFWorkbook( WorkbookUtils.loadFile( pathToFile ) );
    }
}
