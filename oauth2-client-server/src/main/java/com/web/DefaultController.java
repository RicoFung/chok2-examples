package com.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileInputStream;
import java.security.KeyStore;

@Controller
public class DefaultController {

    @Value("${app.base-uri}")
    private String appBaseUri;

    @GetMapping("/")
    public String root() {
        return "redirect:" + this.appBaseUri + "/#/welcome";
    }

    // '/authorized' is the registered 'redirect_uri' for authorization_code
    @GetMapping("/authorized")
    public String authorized() {
        return "redirect:" + this.appBaseUri;
    }


        public static void main(String[] args) throws Exception {
            FileInputStream fis = new FileInputStream("C:/Dev/projects/jee/chok2-examples/oauth2-client-server/src/main/resources/localhost.p12");
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(fis, "888888".toCharArray());
            System.out.println("KeyStore Loaded Successfully!");
        }
}