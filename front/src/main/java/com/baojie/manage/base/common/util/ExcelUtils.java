package com.baojie.manage.base.common.util;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import io.swagger.annotations.ApiModelProperty;

/**
 * excel导入导出的工具类（支持xls和xlsx格式）
 */
public class ExcelUtils {

    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel

    private static XSSFCellStyle fontStyle;
    private static XSSFCellStyle fontStyle2;

    /*************************************文件上传****************************/

    public static List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception {
        List<List<Object>> list = null;
        //创建Excel工作薄    
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet    
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            //遍历当前sheet中的所有行    
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                //遍历所有的列    
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(getCellValue(cell));
                }
                list.add(li);
            }
        }
//        work.close();    
        return list;
    }


    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-    
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+    
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Object getCellValue(Cell cell) {
        Object value = null;
        //格式化number String字符
        DecimalFormat df = new DecimalFormat("0");
        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        //格式化数字
        DecimalFormat df2 = new DecimalFormat("0.00");

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

    /****************************************上传结束*************************************** 


     /**
     * 多列头创建EXCEL 
     *
     * @param sheetName 工作簿名称 
     * @param clazz  数据源model类型 
     * @param objs   excel标题列以及对应model字段名 
     * @param map  标题列行数以及cell字体样式 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static XSSFWorkbook createExcelFile(Class clazz, List objs, Map<Integer, List<ExcelBean>> map, String sheetName) throws Exception {
        // 创建新的Excel 工作簿  
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称  
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // 以下为excel的字体样式以及excel的标题与内容的创建，下面会具体分析;
        //字体样式
        createFont(workbook);
        //创建标题（头）
        createTableHeader(sheet, map);
        //创建内容
        createTableRows(sheet, map, objs, clazz);
        return workbook;
    }

    /**
     * 多列头创建EXCEL- 增加 和分页
     *
     * @param sheetName 工作簿名称
     * @param clazz     数据源model类型
     * @param objs      excel标题列以及对应model字段名
     * @param map       标题列行数以及cell字体样式
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static XSSFWorkbook createExcelFile(Class clazz, List objs, Map<Integer, List<ExcelBean>> map, String sheetName, XSSFWorkbook workbook) throws Exception {
        // 创建新的Excel 工作簿
        if (workbook == null) {
            workbook = new XSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
            XSSFSheet sheet = workbook.createSheet(sheetName);
            // 以下为excel的字体样式以及excel的标题与内容的创建，下面会具体分析;
            //字体样式
            createFont(workbook);
            //创建标题（头）
            createTableHeader(sheet, map);
            //创建内容
            createTableRows(sheet, map, objs, clazz);
           // return workbook;
        }
        //获取
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
            sheet = workbook.createSheet(sheetName);
            // 以下为excel的字体样式以及excel的标题与内容的创建，下面会具体分析;
            //创建标题（头）
            createTableHeader(sheet, map);
            //创建内容
            createTableRows(sheet, map, objs, clazz);
           // return workbook;
        }
        //创建内容
        createTableRows(sheet, map, objs, clazz);
        return workbook;
    }


    public static void createFont(XSSFWorkbook workbook) {
        // 表头  
        fontStyle = workbook.createCellStyle();
        XSSFFont font1 = workbook.createFont();
//        font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  
        font1.setFontName("黑体");
        font1.setFontHeightInPoints((short) 14);// 设置字体大小  
        fontStyle.setFont(font1);
//        fontStyle.setBorderBottom(XSSFCellStyleBORDER_THIN); // 下边框  
//        fontStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框  
//        fontStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框  
//        fontStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框  
//        fontStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中  

        // 内容  
        fontStyle2 = workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 10);// 设置字体大小  
        fontStyle2.setFont(font2);
//        fontStyle2.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框  
//        fontStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框  
//        fontStyle2.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框  
//        fontStyle2.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框  
//        fontStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中  
    }

    /**
     * 根据ExcelMapping 生成列头（多行列头）
     *
     * @param sheet 工作簿
     * @param map   每行每个单元格对应的列头信息
     */
    public static final void createTableHeader(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map) {
        int startIndex = 0; //cell起始位置
        int endIndex = 0; //cell终止位置
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            XSSFRow row = sheet.createRow(entry.getKey());
            List<ExcelBean> excels = entry.getValue();
            for (int x = 0; x < excels.size(); x++) {
                //合并单元格  
                if (excels.get(x).getCols() > 1) {
                    if (x == 0) {
                        endIndex += excels.get(x).getCols() - 1;
                        CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                        startIndex += excels.get(x).getCols();
                    } else {
                        endIndex += excels.get(x).getCols();
                        CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                        startIndex += excels.get(x).getCols();
                    }
                    XSSFCell cell = row.createCell(startIndex - excels.get(x).getCols());
                    cell.setCellValue(excels.get(x).getHeadTextName());// 设置内容  
                    if (excels.get(x).getCellStyle() != null) {
                        cell.setCellStyle(excels.get(x).getCellStyle());// 设置格式  
                    }
                    cell.setCellStyle(fontStyle);
                } else {
                    XSSFCell cell = row.createCell(x);
                    cell.setCellValue(excels.get(x).getHeadTextName());// 设置内容  
                    if (excels.get(x).getCellStyle() != null) {
                        cell.setCellStyle(excels.get(x).getCellStyle());// 设置格式  
                    }
                    cell.setCellStyle(fontStyle);
                }
            }
        }
    }

    /**
     * 找到指定下标插入一个row,不覆盖已存在得row
     *
     * @param sheet
     * @param rowIndex
     * @return
     */
    private static XSSFRow insertRow(XSSFSheet sheet, Integer rowIndex) {
        XSSFRow row;
        if (sheet.getRow(rowIndex) != null) {
            int lastRowNo = sheet.getLastRowNum();
            sheet.shiftRows(rowIndex, lastRowNo, 1);
        }
        row = sheet.createRow(rowIndex);
        return row;
    }

    /**
     * @param sheet
     * @param map
     * @param objs
     * @param clazz
     */
    @SuppressWarnings({"rawtypes", "deprecation"})
    public static void createTableRows(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map, List objs, Class clazz) throws Exception {
        int rowindex = map.size();
        int maxKey = 0;
        List<ExcelBean> ems = new ArrayList<>();
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        ems = map.get(maxKey);

        List<Integer> widths = new ArrayList<Integer>(ems.size());
        for (Object obj : objs) {
            XSSFRow row = sheet.createRow(rowindex);
            for (int i = 0; i < ems.size(); i++) {
                ExcelBean em = (ExcelBean) ems.get(i);
                // 获得get方法
                PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object rtn = getMethod.invoke(obj);
                String value = "";
                // 如果是日期类型 进行 转换  
                if (rtn != null) {
                    if (rtn instanceof Date) {
                        value = DateUtil.date2String((Date) rtn, "yyyy-MM-dd");
                    } else if (rtn instanceof BigDecimal) {
                        NumberFormat nf = new DecimalFormat("#,##0.00");
                        value = nf.format((BigDecimal) rtn).toString();
                    } else if ((rtn instanceof Integer) && (Integer.valueOf(rtn.toString()) < 0)) {
                        value = "--";
                    } else {
                        value = rtn.toString();
                    }
                }
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(value);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(fontStyle2);
                // 获得最大列宽  
                int width = value.getBytes().length * 300;
                // 还未设置，设置当前  
                if (widths.size() <= i) {
                    widths.add(width);
                    continue;
                }
                // 比原来大，更新数据  
                if (width > widths.get(i)) {
                    widths.set(i, width);
                }
            }
            rowindex++;
        }
        // 设置列宽  
        for (int index = 0; index < widths.size(); index++) {
            Integer width = widths.get(index);
            width = width < 2500 ? 2500 : width + 300;
            width = width > 10000 ? 10000 + 300 : width + 300;
            sheet.setColumnWidth(index, width);
        }
    }


    /**
     * 对sheet添加数据,允许多次拼接数据
     * @param sheet
     * @param map
     * @param objs
     * @param clazz
     */
    @SuppressWarnings({"rawtypes", "deprecation"})
    public static void concatTableRows(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map, List objs, Class clazz) throws Exception {
        int rowindex = map.size();
        int maxKey = 0;
        List<ExcelBean> ems = new ArrayList<>();
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        ems = map.get(maxKey);

        List<Integer> widths = new ArrayList<Integer>(ems.size());
        for (Object obj : objs) {
            XSSFRow row = insertRow(sheet, rowindex);
            for (int i = 0; i < ems.size(); i++) {
                ExcelBean em = (ExcelBean) ems.get(i);
                // 获得get方法
                PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object rtn = getMethod.invoke(obj);
                String value = "";
                // 如果是日期类型 进行 转换
                if (rtn != null) {
                    if (rtn instanceof Date) {
                        value = DateUtil.date2String((Date) rtn, "yyyy-MM-dd");
                    } else if (rtn instanceof BigDecimal) {
                        NumberFormat nf = new DecimalFormat("#,##0.00");
                        value = nf.format((BigDecimal) rtn).toString();
                    } else if ((rtn instanceof Integer) && (Integer.valueOf(rtn.toString()) < 0)) {
                        value = "--";
                    } else {
                        value = rtn.toString();
                    }
                }
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(value);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(fontStyle2);
                // 获得最大列宽
                int width = value.getBytes().length * 300;
                // 还未设置，设置当前
                if (widths.size() <= i) {
                    widths.add(width);
                    continue;
                }
                // 比原来大，更新数据
                if (width > widths.get(i)) {
                    widths.set(i, width);
                }
            }
            rowindex++;
        }
        // 设置列宽
        for (int index = 0; index < widths.size(); index++) {
            Integer width = widths.get(index);
            width = width < 2500 ? 2500 : width + 300;
            width = width > 10000 ? 10000 + 300 : width + 300;
            sheet.setColumnWidth(index, width);
        }
    }

    /**
     * 通过bean注解获取excel head
     * @param bean
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map<Integer,List<ExcelBean>> getWorkbookHeader(Class bean){
        Map<Integer,List<ExcelBean>> map = new HashMap<Integer,List<ExcelBean>>();
        List<ExcelBean> list = new ArrayList<ExcelBean>();
        Field[] fields =  bean.getDeclaredFields();
        ExcelBean excelBean ;
        for(Field f : fields){

            String filedName = f.getName();
            //1、获取属性上的指定类型的注解
            Annotation annotation = f.getAnnotation(ApiModelProperty.class);
            if(annotation!=null) {
                //强制转化为相应的注解
                ApiModelProperty apiModelProperty = (ApiModelProperty)annotation;
                //3、获取属性上的指定类型的注解的指定方法
                //具体是不是默认值可以去查看源代码
                excelBean = new ExcelBean();
                excelBean.setCols(1);
                excelBean.setHeadTextName(apiModelProperty.value());
                excelBean.setPropertyName(filedName);
                list.add(excelBean);
            }
        }
        map.put(0, list);
        return map;
    }

}

