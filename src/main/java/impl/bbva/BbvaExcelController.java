package impl.bbva;

import controller.AbstractExcelController;
import model.AbstractRowModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.CellUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BbvaExcelController extends AbstractExcelController {

    public void translateData(){
        XSSFWorkbook reader = ( XSSFWorkbook ) super.getExcelDataReader();
        // XSSFWorkbook writer = ( XSSFWorkbook ) getExcelDataWriter();

        // TODO: Encapsulate this into a more general sheet/row provider, each provider should be adapted to a specific Excel structure in
        // order to avoid coupling
        XSSFSheet workingReaderSheet = reader.getSheetAt( reader.getNumberOfSheets() - 1 );

        // XSSFSheet workingWriterSheet = writer.getSheetAt( writer.getNumberOfSheets() - 1 );

        List<AbstractRowModel> readerRows = extractRowData( workingReaderSheet );

        readerRows.forEach( System.out::println );

    }

    public List<AbstractRowModel> extractRowData( XSSFSheet workingReaderSheet ) {
        Iterator<Row> rowIterator = workingReaderSheet.rowIterator();

        List<AbstractRowModel> results = new ArrayList<>();

        while( rowIterator.hasNext() ){
            Row row = rowIterator.next();
            results.add( buildModel( row ) );
        }

        return results;
    }

    public void insertRowData( XSSFSheet workingWriterSheet, List<AbstractRowModel> inputList ){

    }

    public AbstractRowModel buildModel( Row row ){
        Date operationDate = CellUtils.extractDateFromStringCell( getBbvaDateString( row ) );
        String receiptNumber = getBbvaReceiptString( row );
        String description = getBbvaDescriptionString( row );
        BigDecimal amount = new BigDecimal( CellUtils.trimNumericString( getBbvaDecimalString( row ) ) );

        return new BbvaRowModel( operationDate, receiptNumber, description, amount );
    }

    // Helper Methods
    private String getBbvaDecimalString( Row row ) {
        return row.getCell( 3 ).getStringCellValue();
    }

    private String getBbvaDescriptionString( Row row ) {
        return row.getCell( 2 ).getStringCellValue();
    }

    private String getBbvaReceiptString( Row row ) {
        return row.getCell( 1 ).getStringCellValue();
    }

    private String getBbvaDateString(Row row ) {
        String[] splitDateString = row.getCell( 0 ).getStringCellValue().trim().split( "T" );
        return splitDateString[ 0 ];
    }
}
