//package org.cityu.cs.ian.util;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.ArrayList;
//
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.DataFormat;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.streaming.SXSSFSheet;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//
///**
// * Excel 导出工具类 -2007
// */
//public class ExportExcelFile {
//
//	private final int SPLIT_COUNT = 100000; //Excel每个工作簿的行数
//
//	private ArrayList<String> fieldName = null; //excel标题数据集
//
//	private ArrayList<ArrayList<String>> fieldData = null; //excel数据内容
//
//	private SXSSFWorkbook workBook = null;
//
//	/**
//	 * 构造器
//	 * @param fieldName 结果集的字段名
//	 * @param fieldData
//	 */
//	public ExportExcelFile(ArrayList<String> fieldName, ArrayList<ArrayList<String>> fieldData) {
//
//		this.fieldName = fieldName;
//		this.fieldData = fieldData;
//	}
//
//	/**
//	 * 创建HSSFWorkbook对象
//	 * @return HSSFWorkbook
//	 */
//	public SXSSFWorkbook createWorkbook() {
//
//		workBook = new SXSSFWorkbook(100);//创建一个工作薄对象
//		int rows = 0;
//		int sheetNum = 0;           //指定sheet的页数
//
//		DataFormat formatt= workBook.createDataFormat();
//		CellStyle dataStyle = workBook.createCellStyle();
//		dataStyle.setDataFormat(formatt.getFormat("yyyy/M/d"));
//
//		try{
//			rows = fieldData.size();//总的记录数
//			if (rows % SPLIT_COUNT == 0) {
//				sheetNum = rows / SPLIT_COUNT;
//			} else {
//				sheetNum = rows / SPLIT_COUNT + 1;
//			}
//			for (int i = 1; i <= sheetNum; i++) {//循环2个sheet的值
//				Sheet sheet = workBook.createSheet("Page " + i);//使用workbook对象创建sheet对象
//				Row headRow = sheet.createRow(0); //创建行，0表示第一行（本例是excel的标题）
//				for (int j = 0; j < fieldName.size(); j++) {//循环excel的标题
//					Cell cell = headRow.createCell( j);//使用行对象创建列对象，0表示第1列
//					/**************对标题添加样式begin********************/
//					//设置列的宽度
//					sheet.setColumnWidth(j, 6000);
//					CellStyle cellStyle = workBook.createCellStyle();//创建列的样式对象
//					Font font = workBook.createFont();//创建字体对象
//					//字体加粗
//					font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//					//字体颜色变红
//					font.setColor(HSSFColor.RED.index);
//					//如果font中存在设置后的字体，并放置到cellStyle对象中，此时该单元格中就具有了样式字体
//					cellStyle.setFont(font);
//					/**************对标题添加样式end********************/
//
//					if(fieldName.get(j) != null){
//						//将创建好的样式放置到对应的单元格中
//						cell.setCellStyle(cellStyle);
//						cell.setCellValue((String) fieldName.get(j));//为标题中的单元格设置值
//					}else{
//						cell.setCellValue("-");
//					}
//				}
//				//分页处理excel的数据，遍历所有的结果
//				for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
//					if (((i - 1) * SPLIT_COUNT + k) >= rows)//如果数据超出总的记录数的时候，就退出循环
//						break;
//					Row row = sheet.createRow((k + 1));//创建1行
//					//分页处理，获取每页的结果集，并将数据内容放入excel单元格
//					ArrayList<String> rowList = (ArrayList<String>) fieldData.get((i - 1) * SPLIT_COUNT + k);
//					for (int n = 0; n < rowList.size(); n++) {//遍历某一行的结果
//						Cell cell = row.createCell( n);//使用行创建列对象
//						String obj=rowList.get(n).toString().trim();
//						if(!StringUtil.isNullorEmpty(obj)){
//							cell.setCellValue(obj);
//						}else{
//							cell.setCellValue("");
//						}
//					}
//					//释放内存
//					if(i%100==0){
//						((SXSSFSheet)sheet).flushRows(100);
//					}
//				}
//			}
//		}catch (Exception e){
//			System.out.println("导出报表创建excel文件出错");
//		}
//		return workBook;
//	}
//
//	public void expordExcel(OutputStream os){
//		workBook = createWorkbook();
//		try {
//			workBook.write(os);//将excel中的数据写到输出流中，用于文件的输出
//		} catch (IOException e) {
//			System.out.println("流写入文件出错");
//		}finally {
//			try {
//				os.close();
//			} catch (IOException e) {
//				System.out.println("流关闭出错");
//			}
//		}
//
//	}
//
//}
