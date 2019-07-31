package SpringBoot.util;

import SpringBoot.domain.Person;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


public class ExcelGenerator {

    public static ByteArrayInputStream customersToExcel(List<Person> persons) throws IOException {
//        String[] COLUMNs = {"id", "f", "i", "o"};
//        try {
//            try (
//                     XSSFWorkbook ff = new XSSFWorkbook();
//                    //XSSFWorkbook workbook = new XSSFWorkbook();
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//            ) {
//                CreationHelper createHelper = workbook.getCreationHelper();
//
//                Sheet sheet = workbook.createSheet("Persons");
//
//                Font headerFont = workbook.createFont();
//                headerFont.setItalic(true);
//                headerFont.setColor(IndexedColors.BLUE.getIndex());
//
//                CellStyle headerCellStyle = workbook.createCellStyle();
//                headerCellStyle.setFont(headerFont);
//
//                // Row for Header
//                Row headerRow = sheet.createRow(0);
//
//                // Header
//                for (int col = 0; col < COLUMNs.length; col++) {
//                    Cell cell = headerRow.createCell(col);
//                    cell.setCellValue(COLUMNs[col]);
//                    cell.setCellStyle(headerCellStyle);
//                }
//
//                // CellStyle for Age
//                CellStyle ageCellStyle = workbook.createCellStyle();
//                ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
//
//                int rowIdx = 1;
//                for (Person person : persons) {
//                    Row row = sheet.createRow(rowIdx++);
//
//                    row.createCell(0).setCellValue(person.getId());
//                    row.createCell(1).setCellValue(person.getF());
//                    row.createCell(1).setCellValue(person.getI());
//                    row.createCell(2).setCellValue(person.getO());
//
////                    Cell ageCell = row.createCell(3);
////                    ageCell.setCellValue(person.getAge());
////                    ageCell.setCellStyle(ageCellStyle);
//                }
//
//                workbook.write(out);
//                return new ByteArrayInputStream(out.toByteArray());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
