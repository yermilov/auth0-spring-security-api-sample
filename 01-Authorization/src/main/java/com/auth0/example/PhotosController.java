package com.auth0.example;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Component
public class PhotosController {

    @CrossOrigin
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login() {
        return "{ \"message\" : \"some login\" }";
    }

    @CrossOrigin
    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    @ResponseBody
    public String getPhotos() {
        return "{ \"message\" : \"some photos\" }";
    }
}
