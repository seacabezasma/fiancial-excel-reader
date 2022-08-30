package controller;

import factory.WorkbookProviderFactory;
import model.AbstractRowModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;
import java.util.List;

public abstract class AbstractExcelController {

    protected Workbook getExcelDataReader(){
        Workbook excelReader;
        try {
            // TODO; Parametrise/Externalize path somehow
            excelReader = WorkbookProviderFactory.getWorkbookHandler( "src/main/resources/movimientos.xlsx" );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return excelReader;
    }

    protected Workbook getExcelDataWriter(){
        Workbook excelWriter;
        try {
            // TODO; Parametrise/Externalize path somehow
            excelWriter = WorkbookProviderFactory.getWorkbookHandler( "src/main/resources/registro_movimientos.xlsx" );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return excelWriter;
    }

    public abstract void translateData();

    public abstract List<AbstractRowModel> extractRowData( XSSFSheet workingReaderSheet );

    public abstract void insertRowData( XSSFSheet workingWriterSheet, List<AbstractRowModel> inputList );

    public abstract AbstractRowModel buildModel( Row row );
}
