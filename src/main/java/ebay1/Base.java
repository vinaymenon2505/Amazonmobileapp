package ebay1;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import data.Findproduct;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import org.openqa.selenium.interactions.Action;

public class Base
{
	public Action getAction()
	{
		return Driverdetails.getThread().getAction();

	}
	public static List <Findproduct> getDataFromExcel() {
		List <Findproduct> searchList=new ArrayList<Findproduct>();

		try {
			FileInputStream excel=new FileInputStream(new File(System.getProperty("user.dir")+"/resources/TESTDATA.xlsx"));
			XSSFWorkbook book=new XSSFWorkbook(excel);
			XSSFSheet datasheet= book.getSheet("datasheet") ;

			Iterator<Row> sheetIterator=datasheet.iterator();
			while(sheetIterator.hasNext()) {

				Row row=sheetIterator.next();
				Iterator<Cell> cellIterator=row.iterator();

				while(cellIterator.hasNext()) 
				{
					Cell cell=cellIterator.next();
					Findproduct data=new Findproduct();
					switch(cell.getCellType())
                    {
						
		                case STRING:                    	
		                    data.searchstring=cell.getStringCellValue();
		                    break;
		                    
		                case NUMERIC:
		                    //data.add(cell.getNumericCellValue());
		                    break;
		                    
		                /*case BOOLEAN:
		                    data.add(cell.getBooleanCellValue());
		                    break;*/
		                    
						default:
							break;
					
					/*switch(cell.getCellType())
					{
						case Cell.CELL_TYPE_NUMERIC:
							break;
							
						case Cell.CELL_TYPE_STRING:
							Findproduct data=new Findproduct();
							data.searchstring=cell.getStringCellValue();
							searchList.add(data);

							break;
					}*/
				}

			}
			excel.close();
			book.close();
			}
		}
		
		catch ( IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchList;
	}
}
