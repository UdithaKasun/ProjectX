package virtusa;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

public class ExcelReader {
    public static String readFromExcel(String src){
        String output="";
        
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(0);
        
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(src));
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            JSONObject loanObject = new JSONObject();
            
            //Read Recovery Type
            XSSFRow rowRecoveryType = sheet.getRow(7);
            loanObject.put("recoveryType", rowRecoveryType.getCell(8).getStringCellValue());
            
            //Read Loan Reference No
            XSSFRow rowLoanReference = sheet.getRow(8);
            double d = rowLoanReference.getCell(8).getNumericCellValue();
            loanObject.put("loanReference",df.format(d));
            
            //Read Loan Amount
            XSSFRow rowLoanAmount = sheet.getRow(9);
            double amount = rowLoanAmount.getCell(8).getNumericCellValue();
            loanObject.put("loanAmount",amount);
            
            //Read Loan Recovery Account
             XSSFRow rowLoanRecoveryAcc = sheet.getRow(10);
            double loanRecoveryAcc = rowLoanRecoveryAcc.getCell(8).getNumericCellValue();
            loanObject.put("loanRecoveryAcc",df.format(loanRecoveryAcc));
            
            output = loanObject.toString();
        } catch (IOException ex) {
            output="FileNotFound";
        } catch (JSONException ex) {
            output="JSONError";
        }
        
        return output;
    }
}
