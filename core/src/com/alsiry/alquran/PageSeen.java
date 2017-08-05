package com.alsiry.alquran;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alsiry.alquran.book;
import com.alsiry.alquran.book.Stages;
import com.alsiry.alquran.book.Thems;

public class PageSeen extends Actor {
	int page_no ;
	Texture page_textrue; 
	Sprite page_sprite  ;
	Sprite b_a_s,b_b_s,b_c_s ;
	Sprite back_ground  ;
	Sprite britness ;
	
	final float screen_height = Gdx.graphics.getHeight();
	final float screen_width= Gdx.graphics.getWidth(); 
	final float b_height = 0.16f*screen_height ;
	final float b_width = 0.07f*screen_width ;
	final float b_first_x = 0.55f*screen_width; 
	final float b_pading_x = 0.035f*screen_width;
	final float b_y = screen_height - b_height ;
	
	
	boolean  is_me_get_focus = false;
    public PageSeen(int page_no){
		this.page_no = page_no ;

		
		
		
		b_a_s = new Sprite(book.get_region("bookmark1"));
		b_b_s = new Sprite(book.get_region("bookmark2"));
		b_c_s = new Sprite(book.get_region("bookmark3"));
		
		b_a_s.setSize(b_width, b_height);
		
		b_b_s.setSize(b_width, b_height);
		
		b_c_s.setSize(b_width, b_height);

		back_ground = new Sprite(book.get_region("pixel"));
		back_ground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		back_ground.setColor((float)((Math.random()*.2f)+.8f), (float)((Math.random()*.2f)+.8f),
				(float)((Math.random()*.2f)+.8f), 1);

		britness = new Sprite(book.get_region("pixel"));
		britness.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		britness.setColor(0,0,0,0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		if(is_me_get_focus == false){
			if(Math.pow(this.page_no-book.current_page,2)<=book.pages_range_to_load){
				page_textrue=new Texture(Gdx.files.internal("pagestray/p"+page_no+".png"));
				page_textrue.setFilter(TextureFilter.Linear,TextureFilter.Linear);
				page_sprite = new Sprite(page_textrue); 
				page_sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				
				is_me_get_focus = true;
				//book.android.make_toast("page no : "+page_no+" loaded", false);
			}
		}else
		{
			
			b_a_s.setColor(255/255f,27/255f,177/255f,book.bookmark_a_page == page_no? (book.stage_detector==Stages.menu?.6f:(book.them_detector ==Thems.colord?.5f:.4f)):0);
			b_b_s.setColor(27/255f,255/255f,215/255f,book.bookmark_b_page == page_no? (book.stage_detector==Stages.menu?.6f:(book.them_detector ==Thems.colord?.5f:.4f)):0);
			b_c_s.setColor(255/255f,220/255f,27/255f,book.bookmark_c_page == page_no? (book.stage_detector==Stages.menu?.6f:(book.them_detector ==Thems.colord?.5f:.4f)):0);
			
			
			b_a_s.setPosition(b_first_x,book.pages_scroll_pane.getScrollY()-(page_no-1)*Gdx.graphics.getHeight()+ b_y);
			b_b_s.setPosition(b_first_x+b_pading_x+b_width, book.pages_scroll_pane.getScrollY()-(page_no-1)*Gdx.graphics.getHeight()+b_y);
			b_c_s.setPosition(b_first_x+2*b_pading_x+2*b_width, book.pages_scroll_pane.getScrollY()-(page_no-1)*Gdx.graphics.getHeight()+b_y);
		
			back_ground.setPosition(0,book.pages_scroll_pane.getScrollY()-(page_no-1)*Gdx.graphics.getHeight());
			britness.setPosition(0,book.pages_scroll_pane.getScrollY()-(page_no-1)*Gdx.graphics.getHeight());
			
			page_sprite.setPosition(0,book.pages_scroll_pane.getScrollY()-(page_no-1)*Gdx.graphics.getHeight());
			page_sprite.setAlpha(book.stage_detector==Stages.menu?.3f:1);
			back_ground.setAlpha(book.them_detector ==Thems.colord?1:0);
		
			britness.setAlpha(1f-(book.light_value*.01f));
			back_ground.draw(batch);
			page_sprite.draw(batch);
			britness.draw(batch);
			b_a_s.draw(batch);
			b_b_s.draw(batch);
			b_c_s.draw(batch);
		}
		if(Math.pow(this.page_no-book.current_page,2)>book.pages_range_to_load&&is_me_get_focus){
		//	book.android.make_toast("page no : "+page_no+" unloaded", false);
			is_me_get_focus = false ;
			page_sprite = null ;
			page_textrue.dispose() ;
			
			
		}
		
	}
	private float g_r_c(){
		return (float)(((205)+((int)(Math.random()*50)))/255);
	}
	
}
