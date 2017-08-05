package com.alsiry.alquran;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
public class GameActor extends Actor {
Sprite actor_Sprite;
boolean enable_draw  =true; 
boolean enable_rotate = false ;
float rotate_angle ,xpos,ypos,width_,height_;
TextureRegion  actor_texture;
float alpha=1 ; 
float scale =1;
boolean up_or_down_scale = false ;
boolean is_me_live=false; 
boolean enable_grade = false ;
Color actor_color ;
float r,g,b;
public void enable_grade (boolean state){
	enable_grade = state ; 
}
void set_me_live (){
	is_me_live = true;
}
void set_flibing(boolean x , boolean y){
	actor_Sprite.flip(x, y);
}
void set_enable_rotate(boolean state){
	enable_rotate=state;
	rotate_angle=Math.random()>.5?1:-1 ;
	
}
float angle=0 ; 
void set_angle (float angle ){
	this.angle = angle ; 
	actor_Sprite.setOriginCenter();
	actor_Sprite.setRotation(this.angle);
}
void set_enable_rotate(boolean state,boolean right_or_left, int value){
	enable_rotate=state;
	rotate_angle=right_or_left?value:-value ;
	
}
public void set_scale (float scalex, float scaley){
	actor_Sprite.setScale(scalex,scaley);
}
public float get_alpha (){
	return alpha;
}
public void set_color(float r,float g,float b ,float a){
	actor_Sprite.setColor(r/255,g/255,b/255,a);
}
public void set_position (float x , float y ){
	xpos=x; 
	ypos=y ; 
	actor_Sprite.setPosition(x, y);

	setBounds(xpos, ypos, width_, height_);
	
}
	public GameActor(TextureRegion actor_region,float x_pos,float y_pos,Color color,float width,float height) {
	actor_texture = actor_region;
	xpos = x_pos ;
	ypos = y_pos ;
	width_ =width ;
	height_ = height;
	actor_Sprite = new Sprite(actor_texture);
	actor_Sprite.setX(xpos);
	actor_Sprite.setY(ypos);
	if(color != null){   
	actor_Sprite.setColor(color);}
	actor_Sprite.setSize(width_, height_);
	actor_Sprite.setOrigin(width_/2, height_/2);
	setBounds(xpos, ypos, width_, height_);
	this.setTouchable(Touchable.enabled);
	}
	public GameActor(TextureRegion actor_region,float x_pos,float y_pos,Color color,float width,float height,boolean is_loading) {
	actor_texture = actor_region;
	xpos = x_pos ;
	ypos = y_pos ;
	width_ =width ;
	height_ = height;
	actor_Sprite = new Sprite(actor_texture);
	actor_Sprite.setX(xpos);
	actor_Sprite.setY(ypos);
	if(color != null){
	actor_color = color ;
	actor_Sprite.setColor(actor_color);}
	actor_Sprite.setSize(width_, height_);
	
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		if(enable_draw){
			if(enable_rotate){
				actor_Sprite.rotate(rotate_angle);
			}
			if(is_me_live){
				scale = scale+(up_or_down_scale?.002f:-.002f);
				actor_Sprite.setScale(scale);
				if(scale>1.1){
					up_or_down_scale =false;
				}else if(scale<1){
					up_or_down_scale = true;
				}
			}
			
				if(enable_grade){
					r =((0f+((1-(0/*Game.sky.get_day_ritio()*/))*1f)));
					b=r;
					g=r; 
					actor_Sprite.setColor(r, g, b, alpha);

			}
	actor_Sprite.draw(batch);
		}
	}
	public void set_enable_draw (boolean state){
		enable_draw = state;
		this.setVisible(state);
	}
	public void update_alpha(float alph){
		alpha = alph ;
		actor_Sprite.setAlpha(alpha);
	}
	public void update_width(float ritio){
		actor_Sprite.setSize(ritio, actor_Sprite.getHeight());
	}
	public void update_pos_y(float y){
		ypos = y ;
		actor_Sprite.setPosition(xpos, ypos);
	}
	public float get_ypos (){
		return ypos ;
	}
	public float get_xpos(){
		return xpos; 
	}

}
