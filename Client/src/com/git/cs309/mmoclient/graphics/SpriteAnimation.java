package com.git.cs309.mmoclient.graphics;

import java.awt.Graphics;
import java.awt.*;

import com.sun.medialib.mlib.Image;

public class SpriteAnimation {
	 public static void draw(Graphics g){  
	        int mousePreDiffX = 0;
			int mousePreDiffY = 0;
			int towards = 0;
			int px = 0;
			int py = 0;
			java.awt.Image image = null;
	        if(mousePreDiffX == 0&&mousePreDiffY ==0){
				if(towards==1){
	                g.drawImage(image, px-15,py-25, px+65, py+55, 0, 96*3, 96, 96*4, null);  
	            }else if(towards==2){
	                g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);  
	            }else if(towards==3){
	                g.drawImage(image, px-15, py-25, px+65, py+55, 0, 96, 96, 96*2, null);  
	            }else if(towards==4){
	                g.drawImage(image, px-15, py-25, px+65, py+55, 0, 96*2, 96, 96*3, null);  
	            }  
	        }else{
	            if(mousePreDiffX <mousePreDiffY&&mousePreDiffY>0){ 
	                if(mousePreDiffY<5){  
	                	g.drawImage(image, px-15,py-25, px+65, py+55, 0, 96*3, 96, 96*4, null); 
	                }else if(mousePreDiffY<10){  
	                	 g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null); 
	                }else if(mousePreDiffY<15){  
	                	 g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);
	                }else{  
	                	 g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);
	                }  
	            }else if(mousePreDiffX <mousePreDiffY&&mousePreDiffY<0){  
	                if(mousePreDiffY<-5){  
	                	g.drawImage(image, px-15,py-25, px+65, py+55, 0, 96*3, 96, 96*4, null);  
	                }else if(mousePreDiffY<-10){ 
	                	g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);   
	                }else if(mousePreDiffY<-15){  
	                	g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);   
	                }else{  
	                	g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);   
	                }  
	            }else if(mousePreDiffX >mousePreDiffY&&mousePreDiffX<0){  
	                if(mousePreDiffY<-5){  
	                	g.drawImage(image, px-15,py-25, px+65, py+55, 0, 96*3, 96, 96*4, null);  
	                }else if(mousePreDiffY<-10){ 
	                	g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);   
	                }else if(mousePreDiffY<-15){  
	                	g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);   
	                }else{  
	                	g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);   
	                }  
	                  
	            }else if(mousePreDiffX >mousePreDiffY&&mousePreDiffX>0){  
	                if(mousePreDiffY<5){  
	                	g.drawImage(image, px-15,py-25, px+65, py+55, 0, 96*3, 96, 96*4, null);  
	                }else if(mousePreDiffY<10){ 
	                	g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);   
	                }else if(mousePreDiffY<15){  
	                	g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);   
	                }else{  
	                	g.drawImage(image, px-15, py-25, px+65, py+55, 0, 0, 96, 96, null);   
	                }  
	                  
	            }
	        }  
	    }  
}
