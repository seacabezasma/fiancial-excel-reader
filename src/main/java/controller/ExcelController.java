package controller;

import factory.WorkbookProviderFactory;
import impl.bbva.BbvaRowModel;
import model.AbstractRowModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.CellUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelController {

    public void manageData(){
        XSSFWorkbook reader = ( XSSFWorkbook ) getExcelDataReader();
        // XSSFWorkbook writer = ( XSSFWorkbook ) getExcelDataWriter();

        // TODO: Encapsulate this into a more general sheet/row provider, each provider should be adapted to a specific Excel structure in
        // order to avoid coupling
        XSSFSheet workingReaderSheet = reader.getSheetAt( reader.getNumberOfSheets() - 1 );

        // XSSFSheet workingWriterSheet = writer.getSheetAt( writer.getNumberOfSheets() - 1 );

        List<AbstractRowModel> readerRows = ExtractRowData( workingReaderSheet );

        System.out.println( readerRows.stream().toString() );

    }

    // TODO: move this to an interface per writing document structure
    private List<AbstractRowModel> ExtractRowData( XSSFSheet workingReaderSheet ) {
        Iterator<Row> rowIterator = workingReaderSheet.rowIterator();

        List<AbstractRowModel> results = new ArrayList<>();

        while( rowIterator.hasNext() ){
            Row row = rowIterator.next();
            results.add( buildModel( row ) );
        }

        return results;
    }

    private String getBbvaDecimalString( Row row ) {
        return row.getCell( 3 ).getStringCellValue();
    }

    private String getBbvaDescriptionString( Row row ) {
        return row.getCell( 2 ).getStringCellValue();
    }

    private String getBbvaReceiptString( Row row ) {
        return row.getCell( 1 ).getStringCellValue();
    }

    private String getBbvaCellDateString( Row row ) {
        return row.getCell( 0 ).getStringCellValue();
    }

    // TODO; Add to interface
    public AbstractRowModel buildModel( Row row ){
        Date operationDate = CellUtils.extraxctDateFromStringCell( getBbvaCellDateString( row ) );
        String receiptNumber = getBbvaReceiptString( row );
        String description = getBbvaDescriptionString( row );
        BigDecimal amount = new BigDecimal(CellUtils.trimNumericString(getBbvaDecimalString( row ) ) );

        return new BbvaRowModel( operationDate, receiptNumber, description, amount );
    }

    private Workbook getExcelDataReader(){
        Workbook excelReader;
        try {
            // TODO; Parametrise/Externalize path somehow
            excelReader = WorkbookProviderFactory.getWorkbookHandler( "src/main/resources/movimientos.xlsx" );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return excelReader;
    }

    private Workbook getExcelDataWriter(){
        Workbook excelWriter;
        try {
            // TODO; Parametrise/Externalize path somehow
            excelWriter = WorkbookProviderFactory.getWorkbookHandler( "src/main/resources/registro_movimientos.xlsx" );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return excelWriter;
    }
}
