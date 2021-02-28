package dev.akyl.youthcentre.report;

import dev.akyl.youthcentre.report.entity.GenderDTO;
import dev.akyl.youthcentre.report.entity.TeenagerDTO;
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

public class TeenagerReportService {
    public static void getReport(File selectedFile){
        List<TeenagerDTO> teenagerDTOList = getData();
        export(selectedFile, teenagerDTOList);
    }

    private static List<TeenagerDTO> getData(){
        List<TeenagerDTO> teenagerDTOList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Teenager> teenagerList = session.createQuery("from Teenager ", Teenager.class).list();
            if (teenagerList != null) {
                for (Teenager teenager : teenagerList) {
                    TeenagerDTO teenagerDTO = new TeenagerDTO();
                    teenagerDTO.setId(teenager.getId());
                    teenagerDTO.setAddress(teenager.getAddress());
                    teenagerDTO.setBirthday(teenager.getBirthday());
                    teenagerDTO.setContact(teenager.getContact());
                    teenagerDTO.setEmail(teenager.getEmail());
                    teenagerDTO.setFirstName(teenager.getFirstName());
                    teenagerDTO.setInn(teenager.getInn());
                    teenagerDTO.setLastName(teenager.getLastName());
                    teenagerDTO.setMiddleName(teenager.getMiddleName());
                    teenagerDTO.setSex(teenager.getSex());

                    teenagerDTOList.add(teenagerDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teenagerDTOList;
    }

    private static void export(File selectedFile, List<TeenagerDTO> data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Teenager");

        int rowCount = 0;

        Row row = sheet.createRow(++rowCount);

        row.createCell(0).setCellValue("Фамилия");
        row.createCell(1).setCellValue("Имя");
        row.createCell(2).setCellValue("Отчество");
        row.createCell(3).setCellValue("Дата рождения");
        row.createCell(4).setCellValue("ИНН");
        row.createCell(5).setCellValue("Почта");
        row.createCell(6).setCellValue("Контакт");
        row.createCell(7).setCellValue("Адрес");
        row.createCell(8).setCellValue("Пол");

        for (TeenagerDTO rows : data) {
            row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(rows.getLastName());
            row.createCell(1).setCellValue(rows.getFirstName());
            row.createCell(2).setCellValue(rows.getMiddleName());
            row.createCell(3).setCellValue(rows.getBirthday());
            row.createCell(4).setCellValue(rows.getInn());
            row.createCell(5).setCellValue(rows.getEmail());
            row.createCell(6).setCellValue(rows.getContact());
            row.createCell(7).setCellValue(rows.getAddress());
            row.createCell(8).setCellValue(rows.getSex());
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
