package cn.xdh.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.xdh.SomeMethods;
import cn.xdh.entity.Knowledge;
import cn.xdh.entity.Question;
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
        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
            //返回空值
            return null;
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

    /**
     * 读取xls文件内容
     *
     * @return List<Knowledge>对象
     * @throws IOException 输入/输出(i/o)异常
     */
    public static List<Knowledge> readKnowledge(MultipartFile excelFile) throws IOException {
        InputStream is = excelFile.getInputStream();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Knowledge knowledge = null;
        List<Knowledge> list = new ArrayList<Knowledge>();
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
                knowledge = new Knowledge();

                // 循环列Cell
                HSSFCell subject_idCell = hssfRow.getCell(0);
                if (subject_idCell == null) {
                    continue;
                }
                //将名字取出放入知识点实体类中
                knowledge.setSubject_id(Integer.parseInt(getValueOfHSSFCell(subject_idCell)));

                HSSFCell stage_idCell = hssfRow.getCell(1);
                if (stage_idCell == null) {
                    continue;
                }
                knowledge.setStage_id(Integer.parseInt(getValueOfHSSFCell(stage_idCell)));

                HSSFCell titleCell = hssfRow.getCell(2);
                if (titleCell == null) {
                    continue;
                }
                knowledge.setTitle(getValueOfHSSFCell(titleCell));
                knowledge.setAdd_time(SomeMethods.getCurrentTime());
                list.add(knowledge);
            }
        }
        is.close();
        return list;
    }

    public static List<Knowledge> readKnowledgeXlsx(MultipartFile excelFile) throws IOException {
        InputStream is = excelFile.getInputStream();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Knowledge knowledge = null;
        List<Knowledge> list = new ArrayList<Knowledge>();
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
                knowledge = new Knowledge();

                // 循环列Cell
                XSSFCell subject_idCell = xssfRow.getCell(0);
                if (subject_idCell == null) {
                    continue;
                }
                //将名字取出放入知识点实体类中
                knowledge.setSubject_id(Integer.parseInt(getValueOfXSSFCell(subject_idCell)));

                XSSFCell stage_idCell = xssfRow.getCell(1);
                if (stage_idCell == null) {
                    continue;
                }
                knowledge.setStage_id(Integer.parseInt(getValueOfXSSFCell(stage_idCell)));

                XSSFCell titleCell = xssfRow.getCell(2);
                if (titleCell == null) {
                    continue;
                }
                knowledge.setTitle(getValueOfXSSFCell(titleCell));
                knowledge.setAdd_time(SomeMethods.getCurrentTime());
                list.add(knowledge);
            }
        }
        is.close();
        //System.out.println(list);
        return list;
    }

    /**
     * 读取xls文件内容
     *
     * @return List<Question>对象
     * @throws IOException 输入/输出(i/o)异常
     */
    public static List<Question> readQuestion(MultipartFile excelFile) throws IOException {
        InputStream is = excelFile.getInputStream();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Question question = null;
        List<Question> list = new ArrayList<Question>();
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
                question = new Question();

                // 循环列Cell
                HSSFCell subject_idCell = hssfRow.getCell(0);
                if (subject_idCell == null) {
                    continue;
                }
                //将名字取出放入试题实体类中
                question.setSubject_id(Integer.parseInt(getValueOfHSSFCell(subject_idCell)));

                HSSFCell stage_idCell = hssfRow.getCell(1);
                if (stage_idCell == null) {
                    continue;
                }
                question.setStage_id(Integer.parseInt(getValueOfHSSFCell(stage_idCell)));

                HSSFCell point_idCell = hssfRow.getCell(2);
                if (point_idCell == null) {
                    continue;
                }
                question.setPoint_id(Integer.parseInt(getValueOfHSSFCell(point_idCell)));

                HSSFCell type_idCell = hssfRow.getCell(3);
                if (type_idCell == null) {
                    continue;
                }
                question.setType_id(Integer.parseInt(getValueOfHSSFCell(type_idCell)));

                HSSFCell titleCell = hssfRow.getCell(4);
                if (titleCell == null) {
                    continue;
                }
                question.setTitle(getValueOfHSSFCell(titleCell));

                HSSFCell optionACell = hssfRow.getCell(5);
                if (optionACell != null) {
                    question.setOptionA(getValueOfHSSFCell(optionACell));
                }

                HSSFCell optionBCell = hssfRow.getCell(6);
                if (optionBCell != null) {
                    question.setOptionB(getValueOfHSSFCell(optionBCell));
                }

                HSSFCell optionCCell = hssfRow.getCell(7);
                if (optionCCell != null) {
                    question.setOptionC(getValueOfHSSFCell(optionCCell));
                }

                HSSFCell optionDCell = hssfRow.getCell(8);
                if (optionDCell != null) {
                    question.setOptionD(getValueOfHSSFCell(optionDCell));
                }

                HSSFCell answerCell = hssfRow.getCell(9);
                if (answerCell == null) {
                    continue;
                }
                question.setAnswer(getValueOfHSSFCell(answerCell));

                HSSFCell scoreCell = hssfRow.getCell(10);
                if (scoreCell == null) {
                    continue;
                }
                question.setScore(Integer.parseInt(getValueOfHSSFCell(scoreCell)));

                question.setAdd_time(SomeMethods.getCurrentTime());

                list.add(question);
            }
        }
        is.close();
        return list;
    }

    public static List<Question> readQuestionXlsx(MultipartFile excelFile) throws IOException {
        InputStream is = excelFile.getInputStream();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Question question = null;
        List<Question> list = new ArrayList<Question>();
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
                question = new Question();

                // 循环列Cell
                XSSFCell subject_idCell = xssfRow.getCell(0);
                if (subject_idCell == null) {
                    continue;
                }
                //将名字取出放入学生实体类中
                question.setSubject_id(Integer.parseInt(getValueOfXSSFCell(subject_idCell)));

                XSSFCell stage_idCell = xssfRow.getCell(1);
                if (stage_idCell == null) {
                    continue;
                }
                question.setStage_id(Integer.parseInt(getValueOfXSSFCell(stage_idCell)));


                XSSFCell point_idCell = xssfRow.getCell(2);
                if (point_idCell == null) {
                    continue;
                }
                question.setPoint_id(Integer.parseInt(getValueOfXSSFCell(point_idCell)));

                XSSFCell type_idCell = xssfRow.getCell(3);
                if (type_idCell == null) {
                    continue;
                }
                question.setType_id(Integer.parseInt(getValueOfXSSFCell(type_idCell)));

                XSSFCell titleCell = xssfRow.getCell(4);
                if (titleCell == null) {
                    continue;
                }
                question.setTitle(getValueOfXSSFCell(titleCell));

                XSSFCell optionACell = xssfRow.getCell(5);
                if (optionACell != null) {
                    question.setOptionA(getValueOfXSSFCell(optionACell));
                }

                XSSFCell optionBCell = xssfRow.getCell(6);
                if (optionBCell != null) {
                    question.setOptionB(getValueOfXSSFCell(optionBCell));
                }

                XSSFCell optionCCell = xssfRow.getCell(7);
                if (optionCCell != null) {
                    question.setOptionC(getValueOfXSSFCell(optionCCell));
                }

                XSSFCell optionDCell = xssfRow.getCell(8);
                if (optionDCell != null) {
                    question.setOptionD(getValueOfXSSFCell(optionDCell));
                }

                XSSFCell answerCell = xssfRow.getCell(9);
                if (answerCell == null) {
                    continue;
                }
                question.setAnswer(getValueOfXSSFCell(answerCell));

                XSSFCell scoreCell = xssfRow.getCell(10);
                if (scoreCell == null) {
                    continue;
                }
                question.setScore(Integer.parseInt(getValueOfXSSFCell(scoreCell)));

                question.setAdd_time(SomeMethods.getCurrentTime());
                list.add(question);
            }
        }
        is.close();
        //System.out.println(list);
        return list;
    }



}
