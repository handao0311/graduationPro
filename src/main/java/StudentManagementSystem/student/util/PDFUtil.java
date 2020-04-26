package StudentManagementSystem.student.util;

import StudentManagementSystem.student.dto.StudentScore;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * PDF的操作类
 *
 * @author hctrl
 * @date 2020/4/25 20:51
 */
public class PDFUtil {

    // 创建一个Document对象
    public Document document = new Document();

    //设置字体大小
    private static Font headFont;

    //设置字体大小
    private static Font keyFont;

    //设置字体大小
    private static Font textFont;

    public Integer maxWidth = 520;

    static {

        BaseFont bfChinese;

        try{
            bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            headFont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
            keyFont = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
            textFont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public PDFUtil(File file){

        //设置页面大小
        document.setPageSize(PageSize.A4);

        try{
            PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public PdfPCell createCell(String value,Font font,int align){

        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_BASELINE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value,font));

        return cell;
    }

    public PdfPCell createCell(String value,Font font){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    public PdfPCell createCell(String value,Font font,int align,int colspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    public PdfPCell createCell(String value,Font font,int align,int colspan,boolean boderFlag){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value,font));
        cell.setPadding(3.0f);
        if(!boderFlag){
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }

    public PdfPTable createTable(int colNum){

        PdfPTable table = new PdfPTable(colNum);
        try{
            table.setTotalWidth(maxWidth);
            //table.setLockedWidth(true);

            //设置水平平行
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;

    }

    public PdfPTable createTable(float[] widths){
        PdfPTable table = new PdfPTable(widths);
        try{
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }


    public PdfPTable createBlankTable(){

        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(0);
        table.addCell(createCell("",keyFont));
        table.setSpacingBefore(20.0f);
        table.setSpacingBefore(20.0f);
        return table;

    }

    public void generatePDF(java.util.List<StudentScore> data) throws Exception{

        PdfPTable table = createTable(8);

        table.addCell(createCell("学生成绩列表",keyFont,Element.ALIGN_LEFT,8,false));

        table.addCell(createCell("姓名",keyFont,Element.ALIGN_CENTER));
        table.addCell(createCell("学年", keyFont, Element.ALIGN_CENTER));
        table.addCell(createCell("学期", keyFont, Element.ALIGN_CENTER));
        table.addCell(createCell("课程名", keyFont, Element.ALIGN_CENTER));
        table.addCell(createCell("课程编号", keyFont, Element.ALIGN_CENTER));
        table.addCell(createCell("类型", keyFont, Element.ALIGN_CENTER));
        table.addCell(createCell("学分", keyFont, Element.ALIGN_CENTER));
        table.addCell(createCell("成绩", keyFont, Element.ALIGN_CENTER));

        for (StudentScore studentScore : data){

            table.addCell(createCell(studentScore.getName(), textFont));
            table.addCell(createCell(studentScore.getYear() + "", textFont));
            table.addCell(createCell(studentScore.getTeam(), textFont));
            table.addCell(createCell(studentScore.getCourseName(), textFont));

            table.addCell(createCell(studentScore.getCourseID(), textFont));
            table.addCell(createCell(studentScore.getType() + "", textFont));
            table.addCell(createCell(studentScore.getCredit() + "", textFont));
            table.addCell(createCell(studentScore.getScore() + "", textFont));

        }

        document.add(table);

        document.close();

    }




}
