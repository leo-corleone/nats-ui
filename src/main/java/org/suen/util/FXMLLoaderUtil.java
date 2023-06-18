package org.suen.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.core.io.ClassPathResource;
import org.suen.MainApplication;

import java.io.IOException;

/**
 * @author: suen
 * @time: 2023/6/18
 * @description:
 **/
public class FXMLLoaderUtil {

    public static Parent getParent(String filename){
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(new ClassPathResource(filename).getURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loader.setControllerFactory(MainApplication.getApplicationContext()::getBean);
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
