package com.springapp.mvc.excelWorker;

import com.springapp.mvc.model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.*;
import java.util.List;

/**
 * Created by kot on 14.06.17.
 */

public class ExcelParser{



  private final static XSSFColor YELLOW = new XSSFColor(new java.awt.Color(255, 255, 000));

  private Workbook workBook;
  private Report report;
  private User user;
  private Auto userAuto;

  public void fillTheReport(String filename, Report report, Report prevReport,
                            boolean isDirector, List<Day> days, List<Day> prevReportDays,
                            MainSettings mainSettings, Auto userAuto){

    InputStream inputStream = null;
    OutputStream outputStream = null;
//        XSSFWorkbook workBook = null;
    User user = report.getUser();
    this.report = report;
    this.user = user;
    this.userAuto = userAuto;



    try {
      inputStream = new FileInputStream(filename);
      workBook = new XSSFWorkbook(inputStream);
      outputStream = new FileOutputStream(filename);
      Sheet sheet = workBook.getSheetAt(0);


      workBook.setSheetName(0, report.getPeriod());


//заливка желтым для даты
      XSSFCell cellDate = (XSSFCell) sheet.getRow(13).getCell(1);
      XSSFCellStyle yellowDateStyle = (XSSFCellStyle) cellDate.getCellStyle().clone();
      yellowDateStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
      yellowDateStyle.setFillForegroundColor(YELLOW);


      Cell driver = sheet.getRow(2).getCell(6);
      Cell post = sheet.getRow(2).getCell(7);
      Cell month = sheet.getRow(6).getCell(1);

      Cell currentNorm = sheet.getRow(9).getCell(3);
      Cell winterNorm = sheet.getRow(9).getCell(5);
      Cell summerNorm = sheet.getRow(9).getCell(7);

      Cell auto = sheet.getRow(7).getCell(10);
      Cell autoNumber = sheet.getRow(8).getCell(10);

      Cell summDistance = sheet.getRow(44).getCell(4);
      Cell poluchenoToplivaTotal = sheet.getRow(44).getCell(7);
      Cell summSumm = sheet.getRow(44).getCell(9);
      Cell rashodFactSumm = sheet.getRow(44).getCell(11);
      Cell rashodNormSumm = sheet.getRow(44).getCell(12);
      Cell rashodPoNormeSumm = sheet.getRow(44).getCell(14);

      Cell countOfWorkingDays = sheet.getRow(46).getCell(4);
      Cell underDriver = sheet.getRow(49).getCell(6);



      String surname = "";
      String name = "";
      String patronymic = "";
      String p = "";

      if(user.getSurname() != null){
        surname = user.getSurname();
      }

      if (user.getName() != null){
        name = user.getName();
      }

      if(user.getPatronymic() != null){
        patronymic = user.getPatronymic();
      }

      if(user.getPost() != null){
        p = user.getPost();
      }


//            "Сведения о водителе: " +
      driver.setCellValue(surname + " " + name + " " + patronymic + " " + post);
      post.setCellValue(p);
      month.setCellValue(report.getPeriod());



//            Выбор текущей нормы насхода бензина
      YearMonth yearMonth = report.getYearMonth();


      int monthNumber = report.getMonthNumber();
      if(monthNumber > 3 && monthNumber < 11) {
        if (userAuto.getSummerNorm() != null) {
          currentNorm.setCellValue(userAuto.getSummerNorm());
        }
      }

      else {
        if(userAuto.getWinterNorm() != null){
          currentNorm.setCellValue(userAuto.getWinterNorm());
        }
      }



      if (userAuto.getWinterNorm() != null) {
        winterNorm.setCellValue(userAuto.getWinterNorm());
      } else {
        winterNorm.setCellValue("");
      }


      if (userAuto.getSummerNorm() != null) {
        summerNorm.setCellValue(userAuto.getSummerNorm());
      } else {
        summerNorm.setCellValue("");
      }

      if (userAuto.getBrand() != null) {

        String a = userAuto.getBrand();
        auto.setCellValue(a);

      } else {
        auto.setCellValue("");
      }


      if(userAuto.getNumber() != null){
        String a =  userAuto.getNumber();
        autoNumber.setCellValue(a);
      }
      else {
        autoNumber.setCellValue("");
      }


      int sD = report.getSumKmDistance();
//            summDistance.setCellType(Cell.CELL_TYPE_NUMERIC);
      summDistance.setCellValue(sD);




//            УЗНАТЬ КАК СЧИТАЕТСЯ
      countOfWorkingDays.setCellValue(days.size());




      if(report.getSumSumm() != null) {
//                summSumm.setCellType(Cell.CELL_TYPE_NUMERIC);
        summSumm.setCellValue(report.getSumSumm());
      }
      else {
        summSumm.setCellValue("");
      }






      String subName = "";
      String subPatronymic = "";
      if(name.length()>1){
        subName = name.substring(0,1);
      }
      if (patronymic.length()>1){
        subPatronymic = patronymic.substring(0,1);
      }
      underDriver.setCellValue(surname + " " + subName + "." + subPatronymic + ".");

      int startRow = 13;
      double pTT = 0;

      int countsOfDaysInMonth = yearMonth.lengthOfMonth();


      Collections.sort(days, new Comparator<Day>() {
        @Override
        public int compare(Day o1, Day o2) {
          return o1.getDayNumber() - o2.getDayNumber();
        }
      });



      int count = 1;







      int daysSize = days.size();
      Day lastDayInReport = days.get(daysSize-1);


      for(int i = 0; i<daysSize; i++){
        Day day = days.get(i);
        if(day.getDayNumber() > count ){
          Day newDay = new Day();
          String c = String.valueOf(count);
          if(c.length() == 1){
            c = "0" + c;
          }

          newDay.setDayNumber(Integer.parseInt(c));
          days.add(newDay);
          i--;
        }
        count++;
      }

      while (countsOfDaysInMonth > days.size()){
        Day newDay = new Day();

        String c = String.valueOf(count);
        if(c.length() == 1){
          c = "0" + c;
        }
        newDay.setDayNumber(Integer.parseInt(c));
        days.add(newDay);
        count++;
      }


      Collections.sort(days, new Comparator<Day>() {
        @Override
        public int compare(Day o1, Day o2) {
          return o1.getDayNumber() - o2.getDayNumber();
        }
      });


      for(int j=0; j<days.size(); j++){

        Day day = days.get(j);
        RouteListForDay routeListForDay = new RouteListForDay(day);

//                    дата
        Cell date = sheet.getRow(startRow).getCell(1);



//                   показания спидометра на начало и конец рабочего дня
        Cell startOfWorkingDay = sheet.getRow(startRow).getCell(2);
        Cell endOfWorkingDay = sheet.getRow(startRow).getCell(3);



//                    пробег за день
        Cell dayDistance = sheet.getRow(startRow).getCell(4);

//                    маршрут движения
        Cell route = sheet.getRow(startRow).getCell(6);

//                    получено топлива
        Cell poluchenoTopliva = sheet.getRow(startRow).getCell(7);

//                    цена топлива по чеку за 1 литр
        Cell costByLiter = sheet.getRow(startRow).getCell(8);

//                    итого по чеку
        Cell summ = sheet.getRow(startRow).getCell(9);

//                    расход фактический
        Cell rashodFact = sheet.getRow(startRow).getCell(11);


//                    расход по норме
        Cell rashodNorm = sheet.getRow(startRow).getCell(12);


//                    расход топлива по норме
        Cell rashodToplivaPoNorme = sheet.getRow(startRow).getCell(14);







        StringBuilder r = new StringBuilder();

        List<Point> points = null;
        if(day.getId() != 0) {
          points = day.getPoints();
        }



        if(points != null && !points.isEmpty()){
          r.append(points.get(0).getDeparture().getAddress() + " - ");
          for (int i = 0; i <points.size(); i++){
            r.append(points.get(i).getArrival().getAddress());


            if(i < points.size() - 1) {
              r.append(" - ");
            }


          }
        }


        int dayDistRound = 0;


        if(points != null && !points.isEmpty()) {

          routeListForDay.setIndexes(getRowPointIndexes(points.size()));

          for (int i = 0; i < points.size(); i++) {

            int pointDistanceRounded = new BigDecimal(points.get(i).getDistance()/1000d).setScale(0, RoundingMode.HALF_UP).intValue();
            routeListForDay.setRoutePointValue(i,
                    (points.get(i).getDeparture().getAddress() + " - " + points.get(i).getArrival().getAddress()));
            routeListForDay.setRoutePointDistanceValue(i, pointDistanceRounded);

            dayDistRound += pointDistanceRounded;
          }

        }

//                    routeListForDay.routePointRow = 5;



//                    date.setCellValue(day.getDate());
        date.setCellValue(day.getDayNumber() + "." + report.getMonthNumber() + "." + report.getYear());

        if(day.getDayDistance() == 0) {
//                        String[] dateString = day.getDate().split("\\.");
//                        String[] dateString = day.getDate().split("\\.");

          LocalDate localDate = LocalDate.of(report.getYear(), report.getMonthNumber(), day.getDayNumber());
          if(localDate.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0 || localDate.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0){
            date.setCellStyle(yellowDateStyle);
          }
        }


        if(day.getDayDistance() != 0) {

//                        int res = new BigDecimal(day.getDayDistance()/1000d).setScale(0, RoundingMode.HALF_UP).intValue();
          dayDistance.setCellValue(dayDistRound);

//                        routeListForDay.setRoutePointDistanceValue(routeListForDay.routePointRow, res);
//                        routeListForDay.routePointRow++;
//
//                        routeListForDay.routePointDistance.setCellValue(res);
//                        routeListForDay.routePointRow++;
//                        routeListForDay.distance.setCellValue(res);
          routeListForDay.sumDistance.setCellValue(dayDistRound);




//                        установка показаний одометра для директоров
          if (isDirector) {

            int st = startRow + 1;
            startOfWorkingDay.setCellFormula("(D" + startRow + ")");
            endOfWorkingDay.setCellFormula("(C" + st + "+E" + st + ")");
          }
        }



//установка маршрута с выстой строки в зависимости от длины маршрута
        route.setCellValue(r.toString());
//                    routeListForDay.route.setCellValue(r.toString());
        float rowHeight = route.getRow().getHeightInPoints();
        if(r.toString().length() > 132) {
          route.getRow().setHeightInPoints(rowHeight * 2);
//                        routeListForDay.route.getRow().setHeightInPoints(rowHeight * 2);
        }

        if(r.toString().length() > 250){
          route.getRow().setHeightInPoints(rowHeight * 3);
//                        routeListForDay.route.getRow().setHeightInPoints(rowHeight * 3);
        }





// установка стоимости топлива за литр (если не было заправки на первую поездку, смотрим последнюю цену из предыдущего отчета)

        costByLiter.setCellType(Cell.CELL_TYPE_NUMERIC);
        if(day.getCostByLiter()!=null && day.getSumm()!=null) {
          costByLiter.setCellValue(day.getCostByLiter());
          summ.setCellValue(day.getSumm());
          double d = day.getSumm()/day.getCostByLiter();
          poluchenoTopliva.setCellValue(d);
          rashodFact.setCellValue(d);
          pTT = pTT + d;
        }


        if(day.getDayDistance() != 0 && day.getSumm() == null){

          int k = j;
          while (k>0 && days.get(k).getCostByLiter() == null){
            k--;
          }

          if (k > 0 || (k == 0 && days.get(k).getCostByLiter() != null)) {
            costByLiter.setCellValue(days.get(k).getCostByLiter());
          }

          if(k == 0 && days.get(k).getCostByLiter() == null) {
            if(prevReport != null){
              Collections.sort(prevReportDays, new Comparator<Day>() {
                @Override
                public int compare(Day o1, Day o2) {
//                                        int ch1 = Integer.parseInt(o1.getDate().split("\\.")[0]);
//                                        int ch2 = Integer.parseInt(o2.getDate().split("\\.")[0]);
                  return o2.getDayNumber() - o1.getDayNumber();
                }
              });

              double lastCostByLiter;
              for(int m = 0; m< prevReportDays.size(); m++){
                if(prevReportDays.get(m).getCostByLiter() != null){
                  lastCostByLiter = prevReportDays.get(m).getCostByLiter();
                  costByLiter.setCellValue(lastCostByLiter);
                  break;
                }
              }
            }

            else {
            }
          }
        }



        if(day.getDayDistance() != 0){
          Double dayKmDistance = day.getDayDistance()/1000d;

          double poNorme = new BigDecimal(dayKmDistance*currentNorm.getNumericCellValue()/100).setScale(2, RoundingMode.HALF_UP).doubleValue();
          double rashodPoNorme = new BigDecimal(poNorme*costByLiter.getNumericCellValue()).setScale(2, RoundingMode.HALF_UP).doubleValue();

          rashodNorm.setCellValue(poNorme);
          rashodToplivaPoNorme.setCellValue(rashodPoNorme);
        }


        count++;
        startRow++;

      }


      rashodFactSumm.setCellFormula("SUM(L14:L44)");
      rashodNormSumm.setCellFormula("SUM(M14:M44)");
      rashodPoNormeSumm.setCellFormula("SUM(O14:O44)");




      if(isDirector) {

        XSSFCellStyle style = (XSSFCellStyle) countOfWorkingDays.getCellStyle();
        Set<Cell> forSetStyle = new HashSet<>();

        for(int i=47; i<70; i++){
          sheet.createRow(i);
        }




        Cell pokazania = sheet.getRow(51).createCell(1);
        Cell start = sheet.getRow(51).createCell(3);
        Cell startValue = sheet.getRow(51).getCell(4);

        Cell end = sheet.getRow(52).createCell(3);
        Cell endValue = sheet.getRow(52).createCell(4);

        Cell probeg = sheet.getRow(53).createCell(3);
        Cell probegValue = sheet.getRow(53).createCell(4);

        Cell mediumProbeg = sheet.getRow(54).createCell(3);
        Cell mediumProbegValue = sheet.getRow(54).createCell(4);

        Cell voditel = sheet.getRow(56).createCell(1);
        Cell voditelValue = sheet.getRow(56).createCell(4);

        Cell director = sheet.getRow(58).createCell(1);
        Cell directorValue = sheet.getRow(58).createCell(4);

        Cell buh = sheet.getRow(60).createCell(1);
        Cell buhValue = sheet.getRow(60).createCell(4);


        for(int i=47; i< 70; i++){
          for(int j=0; j<10; j++){
            forSetStyle.add(sheet.getRow(i).getCell(j));
          }
        }

        for(Cell cell: forSetStyle){
          if(cell != null)
            cell.setCellStyle(style);
        }

        pokazania.setCellValue("показания спидометра");
        start.setCellValue("начало мес.");
        end.setCellValue("конец мес.");
        probeg.setCellValue("пробег");
        probegValue.setCellValue(summDistance.getNumericCellValue());
        mediumProbeg.setCellValue("средний пробег");
        mediumProbegValue.setCellValue(probegValue.getNumericCellValue()/countOfWorkingDays.getNumericCellValue());



        voditel.setCellValue("Водитель");
        voditelValue.setCellValue(user.getShortFullName());

        director.setCellValue("Генеральный директор");
        directorValue.setCellValue(mainSettings.getGenDir().getShortFullName());

        buh.setCellValue("Бухгалтер");
        buhValue.setCellValue(mainSettings.getGlavBuh().getShortFullName());



        sheet.setColumnHidden(8, true);
        sheet.setColumnHidden(9, true);
        sheet.setColumnHidden(14, true);



      }






      poluchenoToplivaTotal.setCellValue(pTT);

      workBook.write(outputStream);
      workBook.close();
      inputStream.close();
      outputStream.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  int[] getRowPointIndexes(int length){

//        int[] indexes =  {5,8,10,11,14,17,20,23,26,29,32,35};


//        if(length > 12) {
    int[] indexes = new int[length];
    for (int i = 0; i < length; i++) {
      indexes[i] = i;
    }



//        }




    return indexes;
  }


  class RouteListForDay{




    //        Cell route;
//        Cell distance;
    Cell sumDistance;
    Cell routePoint;
    Cell routePointDistance;

    Sheet newList;

    int routePointRow;



    int[] routepointRowIndexes;






    public void setRoutePointValue(int index, String value) {

      index = 5 + index;
      routePoint = newList.getRow(index).getCell(16);
      routePoint.setCellValue(value);


      float rowHeight = routePoint.getRow().getHeightInPoints();
      if(value.length() > 42) {
        routePoint.getRow().setHeightInPoints(rowHeight * 2);
      }
    }

    public void setRoutePointDistanceValue(int index, int value) {

      index = 5 + index;
      routePointDistance = newList.getRow(index).getCell(22);
      routePointDistance.setCellValue(value);
    }

    public void setIndexes(int[] indexes){
      this.routepointRowIndexes = indexes;
    }

    RouteListForDay(Day d) {
      newList = workBook.cloneSheet(1);
      routePointRow = 5;

      String day = d.getDayNumber() + "";
      String m = report.getMonthNumber() + "";
      if(day.length() == 1){
        day = "0" + day;
      }
      if(m.length() == 1){

        m = "0" + m;
      }
      workBook.setSheetName(1 + d.getDayNumber(), day);

      Cell dateList = newList.getRow(5).createCell(4);
      Cell autoList = newList.getRow(12).getCell(3);
      Cell gosNumber = newList.getRow(13).getCell(5);
//
      Cell dr1 = newList.getRow(14).getCell(2);
      Cell dr2 = newList.getRow(47).getCell(5);
//
//            route = newList.getRow(5).getCell(16);
//            distance = newList.getRow(5).getCell(22);

      sumDistance = newList.getRow(42).getCell(16);

      routePoint = newList.getRow(routePointRow).getCell(16);
      routePointDistance = newList.getRow(routePointRow).getCell(22);
//
      Cell vuNumber = newList.getRow(16).getCell(3);
      Cell vuCat = newList.getRow(16).getCell(11);
      Cell stsNumber = newList.getRow(19).getCell(4);

      Cell fuelType = newList.getRow(32).getCell(10);





      dateList.setCellValue(day + "." + m + "." + report.getYear());

      if (userAuto.getBrand() != null) {
        String a = userAuto.getBrand();
        if(userAuto.getNumber() != null){
          a = a + " " + userAuto.getNumber();
        }
        autoList.setCellValue(a);
      } else {
        autoList.setCellValue("");
      }




      if(userAuto.getNumber() != null){
        String a = userAuto.getNumber();
        gosNumber.setCellValue(a);
      }
      else {
        gosNumber.setCellValue("");
      }


      dr1.setCellValue(user.getSurname() + " " + user.getName() + " " + user.getPatronymic());
      dr2.setCellValue(user.getSurname() + " " + user.getName() + " " + user.getPatronymic());


//            distance.setCellValue(d.getDayDistance());
      sumDistance.setCellValue(d.getDayDistance());

      if(userAuto.getStsNumber() != null){
        stsNumber.setCellValue(userAuto.getStsNumber());
      }


      if(user.getVuCat() != null){
        vuCat.setCellValue(user.getVuCat());
      }

      if(user.getVuNumber() != null){
        vuNumber.setCellValue(user.getVuNumber());
      }

      fuelType.setCellValue(userAuto.getFuelType());



    }











  }

}