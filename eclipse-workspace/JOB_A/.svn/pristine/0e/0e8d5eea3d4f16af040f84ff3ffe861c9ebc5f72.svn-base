package kr.sist.joba.user.controller;

import java.net.URL;

import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MyBrowser extends Region {
	 final String hellohtml = "hello.html";
	 
	    WebView webView = new WebView();
	    WebEngine webEngine = webView.getEngine();
	         
	    public MyBrowser(){
	 
	        URL urlHello = getClass().getResource("PaymentAPI.html");
	        webEngine.load(urlHello.toExternalForm());
	     
	        getChildren().add(webView);
	    }
}
