package cn.xdh.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.xdh.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelToObjectUtil {
    /**
     * 读取xls文件内容
     *
     * @return List<Student>对象
     * @throws IOException 输入/输出(i/o)异常
     */
    public static List<Student> read(MultipartFile excelFile) throws IOException {
        InputStream is = excelFile.getInputStream();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Student student = null;
        List<Student> list = new ArrayList<Student>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                student = new Student();

                // 循环列Cell
                HSSFCell usernameCell = hssfRow.getCell(0);
                if (usernameCell == null) {
                    continue;
                }
                //将名字取出放入学生实体类中
                student.setUsername(getValueOfHSSFCell(usernameCell));

                HSSFCell mobileCell = hssfRow.getCell(1);
                if (mobileCell == null) {
                    continue;
                }
                student.setMobile(getValueOfHSSFCell(mobileCell));

                HSSFCell class_idCell = hssfRow.getCell(2);
                if (class_idCell == null) {
                    continue;
                }
                Integer class_id = Integer.parseInt(getValueOfHSSFCell(class_idCell));
                student.setClass_id(class_id);

                list.add(student);
            }
        }
        is.close();
        return list;
    }

    public static List<Student> readXlsx(MultipartFile excelFile) throws IOException {
        InputStream is = excelFile.getInputStream();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Student student = null;
        List<Student> list = new ArrayList<Student>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }
                student = new Student();

                // 循环列Cell
                XSSFCell usernameCell = xssfRow.getCell(0);
                if (usernameCell == null) {
                    continue;
                }
                //将名字取出放入学生实体类中
                student.setUsername(getValueOfXSSFCell(usernameCell));

                XSSFCell mobileCell = xssfRow.getCell(1);
                if (mobileCell == null) {
                    continue;
                }
                student.setMobile(getValueOfXSSFCell(mobileCell));

                XSSFCell class_idCell = xssfRow.getCell(2);
                if (class_idCell == null) {
                    continue;
                }
                Integer class_id = Integer.parseInt(getValueOfXSSFCell(class_idCell));
                student.setClass_id(class_id);

                list.add(student);
            }
        }
        is.close();
        return list;
    }

    /**
     * 得到Excel表中的值
     *
     * @param hssfCell Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    private static String getValueOfHSSFCell(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            DecimalFormat df = new DecimalFormat("0");
            String strCell = df.format(hssfCell.getNumericCellValue());
            return String.valueOf(strCell);
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    /**
     * 得到Excel表中的值
     *
     * @param xssfCell Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    private static String getValueOfXSSFCell(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            DecimalFormat df = new DecimalFormat("0");
            String strCell = df.format(xssfCell.getNumericCellValue());
            return String.valueOf(strCell);
        } else {
            // 返回字符串类型的值
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
}
