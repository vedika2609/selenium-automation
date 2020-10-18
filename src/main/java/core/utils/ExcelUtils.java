package core.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/*
 * Created by : vedikagupta
 * Date : 03/10/20
 */
public class ExcelUtils {
    public List<LinkedHashMap> getSheetDataBySheetName(String filepath, String sheetName) {
        try {
            filepath = System.getProperty("user.dir") + filepath;
            FileInputStream file = new FileInputStream(new File(filepath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            int sheetNumber = workbook.getSheetIndex(sheetName);
            List<LinkedHashMap> data = xlsxReader(filepath, sheetNumber);
            data.remove(0);
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("file not found");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error in IO");
        }
    }

    private List<LinkedHashMap> xlsxReader(String filepath, int sheetNumber) {
        try {
            List headers = new ArrayList<String>();
            List<LinkedHashMap> excelObject = new ArrayList<>();
            FileInputStream file;
            file = new FileInputStream(new File(filepath));
            XSSFWorkbook workbook;
            workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);
            Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();
            boolean isHeaderRow = true;
            int headersCount = 0;
            DataFormatter dataFormatter = new DataFormatter();
            while (rowIterator.hasNext()) {
                int index = 0;
                LinkedHashMap map = new LinkedHashMap<>();
                org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (isHeaderRow) {
                        if (StringUtils.isNotEmpty(cell.getStringCellValue())) {
                            headers.add(cell.getStringCellValue());
                            map.put(cell.getStringCellValue(), cell.getStringCellValue());
                        }
                    } else {
                        if (index < headersCount) {
                            map.put(headers.get(index), dataFormatter.formatCellValue(cell));
                            index++;
                        }
                    }
                }
                isHeaderRow = false;
                if (!isHeaderRow)
                    headersCount = headers.size();
                excelObject.add(map);
            }
            file.close();
            workbook.close();
            return excelObject;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("file not found");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error in IO");
        }
    }
}