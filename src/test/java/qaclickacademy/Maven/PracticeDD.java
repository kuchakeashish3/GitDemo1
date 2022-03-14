package qaclickacademy.Maven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.bytebuddy.asm.Advice.Return;

public class PracticeDD {
	
	
	public ArrayList<String> getData(String testcaseName) throws IOException
	{
		ArrayList<String> a=new ArrayList<String>();
		FileInputStream fis = new FileInputStream("D://TestData//dataSheet.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int sheets = workbook.getNumberOfSheets();
		
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("Testdata"));
			{
				XSSFSheet sheet=  workbook.getSheetAt(i);
				Iterator <Row> rows =sheet.rowIterator();
			Row firstrow = rows.next();
			Iterator <Cell> cellvalues = firstrow.cellIterator();
			
			int k=0;
			int count = 0;
			while(cellvalues.hasNext())
			{
				Cell value = cellvalues.next();
				if(value.getStringCellValue().equalsIgnoreCase("Testcases"))
				{
					 count = k;
				}
				k++;
			}
			System.out.println(count);
			while(rows.hasNext())
			{
				Row r = rows.next();
			
				if(r.getCell(count).getStringCellValue().equalsIgnoreCase(testcaseName))
				{
					Iterator<Cell> ci = r.cellIterator();
					while(ci.hasNext())
					{
						Cell c =ci.next();
						if(c.getCellType()==CellType.STRING)
						{
					a.add((c.getStringCellValue()));
						}
						else{
							
							a.add(NumberToTextConverter.toText(c.getNumericCellValue()))		;
							
						}
					}
				}
				
			}
			
			}
		}
		return a;
		
		

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		

		

		
	}

}
