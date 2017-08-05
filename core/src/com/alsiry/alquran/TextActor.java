package com.alsiry.alquran;

import org.w3c.dom.css.RGBColor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;

public class TextActor extends Actor {
	BitmapFont font;
    GlyphLayout best_score_text_layout;
    float alpha = 1 ;
    String text_string ;
    
    public enum TextAlign {
    	align_left ,align_right ,align_cinter ;
    }
    TextAlign align  ;
	private  float xpos , ypos, width,scale ;
	Color text_color ;
	boolean enable_draw  =true; 
	public void set_position (float x ,float y){
		xpos = x  ; 
		ypos = y ; 
	}
	public TextActor(String string,Color color,float targetwidth,TextAlign align,float scaleXY ,float x ,float y) {
		// TODO Auto-generated constructor stub
		font = book.base_font; 
		text_string = string ;
		xpos = x; 
		ypos = y; 
		width = targetwidth ;
		 text_color = (color==null?Color.BLACK:color) ;
		 this.align = align;
		 scale = scaleXY;
		font.getData().setScale(scaleXY);
		//font.setColor(Color.BLUE);
		best_score_text_layout = new GlyphLayout(
				font,
				text_string,
				text_color,
				width,
				this.align == TextAlign.align_cinter?Align.center:(this.align==TextAlign.align_right?Align.right:Align.left),
						true);
		
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub	
		if(enable_draw){

	font.draw(batch,best_score_text_layout,
    		xpos,
    		ypos);}
	}
	public void dispose(){
		font.dispose();
	}
	public void update_text (String text){
		text_string = text;/*
		font.getData().setScale(scale);*/
		best_score_text_layout = new GlyphLayout(
				font,
				text_string,
				text_color,
				width,
				this.align == TextAlign.align_left?Align.left:(this.align==TextAlign.align_right?Align.right:Align.center),
						true);
	}
	public void set_enable_draw (boolean state){
		enable_draw = state;
	}
	
}
