package com.alsiry.alquran;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DoaaActor extends Actor {
	TextureRegion doaa_first ,doaa_second ; 
	Sprite doaa_s_first,doaa_s_second ;
	
public DoaaActor() {
	doaa_first = book.images_textures_atlas.findRegion("d1"); 
	doaa_second = book.images_textures_atlas.findRegion("d2"); 
	doaa_s_first = new Sprite(doaa_first) ;
	doaa_s_second = new Sprite(doaa_second); 
	doaa_s_first.setSize(book.screen_width, book.screen_height);
	doaa_s_second.setSize(book.screen_width, book.screen_height);
	
}
@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		
		doaa_s_first.setPosition(0,book.doaa_scroll_pane.getScrollY()-(1-1)*Gdx.graphics.getHeight());
		doaa_s_second.setPosition(0,book.doaa_scroll_pane.getScrollY()-(2-1)*Gdx.graphics.getHeight());
		
		doaa_s_first.draw(batch);
		doaa_s_second.draw(batch);
		
	}
}
