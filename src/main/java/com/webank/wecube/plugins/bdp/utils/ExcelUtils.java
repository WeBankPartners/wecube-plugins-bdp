package com.webank.wecube.plugins.bdp.utils;

import com.webank.wecube.plugins.bdp.common.BdpException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author howechen
 */
public class ExcelUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    private static final int FIRST_INDEX = 0;
    private static final int DATA_START_ROW = 1;

    public static List<Map<String, String>> excelToMap(InputStream inputStream) throws BdpException {
        logger.info("Transferring from excel input stream to map.");
        List<Map<String, String>> result = new ArrayList<>();
        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new BdpException("Cannot transfer excel inputStream to XSSFWorkbook.");
        }
        XSSFSheet sheet = workbook.getSheetAt(FIRST_INDEX);
        Row columnHead = sheet.getRow(FIRST_INDEX);
        Iterator<Cell> columnHeadIterator = columnHead.cellIterator();

        // update columnHeadList
        List<String> columnHeadList = new ArrayList<>();
        while (columnHeadIterator.hasNext()) {
            columnHeadList.add(columnHeadIterator.next().toString());
        }
        logger.info(String.format("Found column head list: [%s]", columnHeadList.toString()));

        // update dataFrame
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        for (int i = DATA_START_ROW; i < physicalNumberOfRows; i++) {
            // data for-loop starts from DATA_START_ROW
            Map<String, String> dataFrame = new LinkedHashMap<>();
            XSSFRow row = sheet.getRow(i);
            int currentRowColumnNum = row.getPhysicalNumberOfCells();

            for (int j = 0; j < currentRowColumnNum; j++) {
                dataFrame.put(columnHeadList.get(j), row.getCell(j).getRawValue());
            }

            if (currentRowColumnNum < columnHeadList.size()) {
                for (int k = currentRowColumnNum; k < columnHeadList.size(); k++) {
                    dataFrame.put(columnHeadList.get(k), "");
                }
            }
            result.add(dataFrame);
        }
        logger.info(String.format("Found data frame list: [%s]", result.toString()));
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        excelToMap(new FileInputStream("/Users/howechen/工作 - confidential/微众/wecube-plugin-bdp/表单示例-用户服务.xlsx"));
    }
}
