package com.wh;

import java.io.File;

/**
 * Created by maciej on 12.10.2017.
 */
public class testMain {

    public static void main(String[] args){

        File f = new File("WebDriver/chromedriver.exe");
        System.out.println(f.getAbsolutePath());

    }



}
