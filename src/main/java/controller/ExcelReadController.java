package controller;

import factory.WorkbookProviderFactory;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

public class ExcelReadController {

    public void readSourceExcelFile(){
        try {
            Workbook excelReader = WorkbookProviderFactory.getWorkbookHandler( "src/main/resources/file.xlsx" );
            int sheetNumber = excelReader.getNumberOfSheets();

            for ( int i = 0; i < sheetNumber; i++ ){
                System.out.println( excelReader.getSheetAt( i ).getSheetName() );
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
