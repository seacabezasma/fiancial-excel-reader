import controller.ExcelReadController;

public class Main {
    public static void main ( String[] args ){
        ExcelReadController readController = new ExcelReadController();

        readController.readSourceExcelFile();
    }
}
