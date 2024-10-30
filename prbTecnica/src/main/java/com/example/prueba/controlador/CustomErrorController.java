package com.example.prueba.controlador;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;


    @Controller
    public class CustomErrorController implements ErrorController {

        @RequestMapping("/error")
        @ResponseBody
        public String handleError(HttpServletRequest request) {
            return "<h1>Ocurrió un error</h1>";
        }
    }
