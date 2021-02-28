package dev.akyl.youthcentre.report;

import dev.akyl.youthcentre.report.entity.GenderDTO;
import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.Teenager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenderReportService {
    public static void getReport(File selectedFile){
        List<GenderDTO> genderDTOS = new ArrayList<>();
        Map<String, Long> data = getData();
        for (Map.Entry<String, Long> item : data.entrySet()) {
            GenderDTO genderDTO = new GenderDTO(item.getKey(), item.getValue());
            genderDTOS.add(genderDTO);
        }
        export(selectedFile, genderDTOS);
    }

    private static Map<String, Long> getData(){
        Map<String, Long> stringLongMap = new HashMap<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Teenager> teenagerList = session.createQuery("from Teenager ", Teenager.class).list();
            if (teenagerList != null) {
                Map<String, Long> map = new HashMap<>();
                for (Teenager teenager : teenagerList) {
                    map.merge(teenager.getSex(), 1L, Long::sum);
                }
                stringLongMap = map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringLongMap;
    }

    private static void export(File selectedFile, List<GenderDTO> data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Gender");

        int rowCount = 0;

        Row row = sheet.createRow(++rowCount);

        row.createCell(0).setCellValue("Пол");
        row.createCell(1).setCellValue("Количество");

        for (GenderDTO rows : data) {
            row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(rows.getGender());
            row.createCell(1).setCellValue(rows.getCount());
        }

        try (FileOutputStream outputStream = new FileOutputStream(selectedFile)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
