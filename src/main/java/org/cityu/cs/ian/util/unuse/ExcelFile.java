//package org.cityu.cs.ian.util;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
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
//  *lxr-excel2007数据导出工具
// */
//public class ExcelFile {
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
//	public ExcelFile(ArrayList<String> fieldName, ArrayList<ArrayList<String>> fieldData) {
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
//		int sheetNum =0;           //指定sheet的页数
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
//			if(sheetNum==0){
//				sheetNum=1;
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
//						if(!StringUtil.issNullorEmpty(obj)){
//							if(judgeDate(obj)){
//							     if(obj.length()==10 && DateUtil.formateDate(obj)!=null){
//									  cell.setCellValue(DateUtil.formateDate(obj));
//								      cell.setCellStyle(dataStyle);
//							     }else if(obj.length()==19){
//							    	 dataStyle.setDataFormat(formatt.getFormat("yyyy/M/d HH:mm:ss"));
//							    	 cell.setCellValue(DateUtil.formateDate2(obj));
//							    	 cell.setCellStyle(dataStyle);
//							 }else{
//								 cell.setCellValue("");
//							 }
//
//						}else{
//							cell.setCellValue(obj);
//						}
//							/*if(n==6||n==7){
//								Date ret=DateUtil.formateDate(obj);
//								 if(ret!=null){
//									 cell.setCellValue(ret);
//								     cell.setCellStyle(dataStyle);
//								 }else{
//									 cell.setCellValue("");
//								 }
//
//							}else{
//								cell.setCellValue(obj);
//							}*/
//						}else{
//							cell.setCellValue("");
//						}
//					}
//					if(i%100==0){
//						((SXSSFSheet)sheet).flushRows(100);
//					}
//				}
//			}
//		}catch (Exception e){
//			e.getStackTrace();
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
//	public boolean isNumeric(String str){ //判断字符串是否是数字
//		   Pattern pattern = Pattern.compile("[0-9]*");
//		   Matcher isNum = pattern.matcher(str);
//		   if( !isNum.matches() ){
//		       return false;
//		   }
//		   return true;
//		}
//	public  boolean  judgeDate(String r){// 判断exel表格中的数据能否转换为时间类型
//		boolean result=true;
//		if(r.indexOf("-")>=0 && r.length()==10 || r.indexOf("-")>=0 && r.length()==19){
//			if(isNumeric(r.substring(0,4)) && r.substring(4, 5).equals("-") && isNumeric(r.substring(5,7)) && r.substring(7, 8).equals("-") && isNumeric(r.substring(8,10))){
//				result=true;
//			}else{
//				result=false;
//			}
//	     }else{
//	    	 result=false;
//	     }
//		 return result;
// }
//
//}
