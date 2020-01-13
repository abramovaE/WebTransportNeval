package com.springapp.mvc.controller;

import com.springapp.mvc.enums.*;
import com.springapp.mvc.model.*;

import com.springapp.mvc.vspom.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;


/**
 * Created by oem on 01.03.18.
 */
@Controller
public class FileLoaderController extends MainController {


    private static final String JPEG = "jpeg";
    private static final String IMAGE_JPEG = "image/jpeg";




    @RequestMapping(value = "/load/{fileType}/{objectId}", method = RequestMethod.GET)
    public String load(Model model, @PathVariable("objectId") Long objectId, @PathVariable("fileType") String fileType){
        String title = "";
        String urlValue = "";
        String formName = "";

        switch (fileType){
            case "sts":
                title = "Загрузить изображение СТС";
                urlValue = "/WebTransport/uploadFile/sts";
//                urlValue = "/uploadFile/sts";
                formName = "uploadStsForm";
                model.addAttribute("date", false);
                break;

            case "osago":

                title = "Загрузить изображение ОСАГО";
                urlValue = "/WebTransport/uploadFile/osago";
//                urlValue = "/uploadFile/osago";
                formName = "uploadOsagoForm";
                model.addAttribute("date", true);
                break;

            case "vu":
                title = "Загрузить изображение водительского удостоверения";
                urlValue = "/WebTransport/uploadFile/vu";
//                urlValue = "/uploadFile/vu";
                formName = "uploadVuForm";
                model.addAttribute("date", true);
                break;
        }


        if(fileType.equals("sts") || fileType.equals("osago")){
            Auto auto = this.autoService.findAutoById(objectId);
            model.addAttribute("object", auto);
        }

        else if(fileType.equals("vu")){
            User user = this.userService.findByIdUser(objectId);
            model.addAttribute("object", user);
        }


        model.addAttribute("title", title);
        model.addAttribute("urlValue", urlValue);
        model.addAttribute("formName", formName);
        return "load";
    }

    @RequestMapping(value = "/uploadFile/{fileType}/{objectId}")
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file, @PathVariable("objectId") Long objectId,
                             @PathVariable("fileType") String fileType,
                             @RequestParam("date") String date
                             ) {// имена параметров (тут - "file") - из формы JSP.


        String name = null;
        String rootPath = null;
        switch (fileType){
            case "sts":
                rootPath = Constants.MAIN_PATH + "/sts";
                break;

            case "osago":
                rootPath = Constants.MAIN_PATH + "/osago";
                break;

            case "vu":
                rootPath = Constants.MAIN_PATH + "/vu";
        }



        String text = null;

//        сохраняем на сервер в папку по пути rootPath
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                name = file.getOriginalFilename();
//                String rootPath = path;

//                String rootPath = "C:\\path\\" ; //try also "C:\path\"

                File dir = new File(rootPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                if(name.contains(" ")){
                    name = name.replaceAll(" ", "_");
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();


                Auto auto = null;
                User user = null;


                text = "Вы успешно загрузили файл " + file.getOriginalFilename();
                switch (fileType){
                    case "sts":
                        auto = this.autoService.findAutoById(objectId);
                        auto.setStsFileName(name);
                        break;

                    case "osago":
                        auto = this.autoService.findAutoById(objectId);
                        auto.setOsagoFileName(name);
                        auto.setOsagoDate(date);
                        break;

                    case "vu":
                        user = this.userService.findByIdUser(objectId);
                        user.setVuFileName(name);
                        user.setVuDate(date);
                        break;

                }

                if(auto != null){
                    this.autoService.updateAuto(auto, autorizedUser);
                    model.addAttribute("user", auto.getUser());
                    model.addAttribute("auto", auto);
                    model.addAttribute("userAutos", auto.getUser().getAutos());

                }

                if (user != null){
                    this.userService.updateUser(user);
                    model.addAttribute("user", user);
                    model.addAttribute("auto", user.getCurrentAuto());
                    model.addAttribute("userAutos", user.getAutos());
                }



            } catch (FileNotFoundException e) {
                text = "Файл " + file.getOriginalFilename() + " не найден";
            } catch (IOException e) {
                text = "Ошибка ввода/вывода";
            }

        }

        else {
            text = "Загрузка не удалась. Файл " + file.getOriginalFilename() + " пуст";
        }



        model.addAttribute("text", text);
        model.addAttribute("accTypes", AccountancyTypes.values());
        model.addAttribute("bodyTypes", BodyTypes.values());
        model.addAttribute("climateMachineTypes", ClimateMachineTypes.values());
        model.addAttribute("fuelTypes", FuelTypes.values());
        model.addAttribute("transmissionTypes", TransmissionTypes.values());
        model.addAttribute("notDriver", false);


        return "editUser";
    }

    @RequestMapping(value = "/look/{fileType}/{objectId}", method = RequestMethod.GET)
    @ResponseBody
    public void lookImage(HttpServletResponse response, @PathVariable("objectId") long objectId,
                          @PathVariable("fileType") String fileType) throws IOException {
        Auto auto;
        User user;
        try {
            File file = null;
            switch (fileType){
                case "sts":
                    auto = this.autoService.findAutoById(objectId);
                    if(auto.getStsFileName() == null)
                    {
                     throw new IllegalArgumentException();
                    }
                    file = new File(Constants.MAIN_PATH +"/sts/" + auto.getStsFileName());
                    break;

                case "osago":
                    auto = this.autoService.findAutoById(objectId);
                    if(auto.getOsagoFileName() == null){
                        throw new IllegalArgumentException();
                    }
                    file = new File(Constants.MAIN_PATH +  "/osago/" + auto.getOsagoFileName());
                    break;

                case "vu":
                    user = this.userService.findByIdUser(objectId);
                    if (user.getVuFileName() == null){
                        throw  new IllegalArgumentException();
                    }
                    file = new File(Constants.MAIN_PATH + "/vu/" + user.getVuFileName());
                    break;
            }


            OutputStream outputStream = response.getOutputStream();
            BufferedImage in = ImageIO.read(file);
            BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = newImage.createGraphics();
            g.drawImage(in, 0, 0, null);
            g.dispose();
            response.setContentType(IMAGE_JPEG);
            ImageIO.write(newImage, JPEG, outputStream);
            outputStream.close();

        } catch (IOException e) {

            e.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");

            // поток для данных ответа
            PrintWriter out = response.getWriter();
            out.println("ошибка ввода/вывода");
            out.close();

        }

        catch (IllegalArgumentException e){
            response.setContentType("text/html; charset=UTF-8");

            // поток для данных ответа
            PrintWriter out = response.getWriter();
            out.println("файл не загружен");
            out.close();
        }

    }



    @RequestMapping(value = "/lookChangesLog/{userId}", method = RequestMethod.GET)
    public String lookChangesLog(Model model, @PathVariable("userId") Long userId){


        User user = this.userService.findByIdUser(userId);
        List<ChangesLog> changesLogList = this.userService.allChangesLogByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("changesLog", changesLogList);

        if(changesLogList == null || changesLogList.isEmpty()) {

            model.addAttribute("text", "Данные пользователя " + user.getShortFullName() + " пока не изменялись");
            return "redirect:/usersManaging/";

        }
        return "changesLog";
    }




}
