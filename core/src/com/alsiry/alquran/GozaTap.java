package com.alsiry.alquran;
import com.alsiry.alquran.TextActor.TextAlign;
import com.alsiry.alquran.book.Stages;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class GozaTap extends Table {
	public static final float tab_width = Gdx.graphics.getWidth();
	public static final float tab_height = Gdx.graphics.getHeight()/12; 
	final float tab_no_width = .13f*tab_width; 
	final float joza_page_no_width = .13f*tab_width ;

	final float ayat_widht = .02f*tab_width; 
	final float sorah_tanzel_width = .1f*tab_width ;
	final float joza_name_width =.41f* tab_width; 
	final float sorah_tab_book_mark_width = .07f*tab_width ;
	final float tab_no_text_scale = tab_height /250 ;
	final float tab_page_text_scale = tab_height /250 ;
	
	public static final float tab_pading_height = tab_height/100; 
	int parts_pages[]={
			1,22,42,
			62,82,102,
			121,142,162,
			182,201,222,
			242,262,282,
			302,322,342,
			362,382,402,
			422,442,462,
			482,502,522,
			542,562,582 ,610//for exeption
		};
	class BookMarks extends Actor{
		TextureRegion bookmark_texture  ;
		Sprite bookmark_sprite ;
		int bookmark_type , no  ;
		boolean bookmark_state = false ;

		float x , y ; 
		public BookMarks(int sorah_no , int type_from1_to3) {
			// TODO Auto-generated constructor stub
			bookmark_type = type_from1_to3 ;
			bookmark_texture = book.images_textures_atlas.findRegion("bookmark"+bookmark_type);
			bookmark_sprite = new Sprite(bookmark_texture) ;
			bookmark_sprite.setSize(sorah_tab_book_mark_width, tab_height);
			no = sorah_no ;
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			// TODO Auto-generated method stub
			int checker = 0 ;
			int checker_a=0; 
			float r,g,b  ;
			r=g=b=0 ;
			super.draw(batch, parentAlpha);
			switch (bookmark_type) {
			case 1:
				checker = book.bookmark_a_page;
				
				r =1 ; 
				g =.105f ; 
				b =0.694f; 
				
				
				break;
			case 2:
				checker = book.bookmark_b_page; 
				r =.105f ; 
				g =1 ; 
				b =0.843f; 
				
				break;
			case 3:
				checker = book.bookmark_c_page; 
				r =1 ; 
				g =.862f; 
				b =.105f ; 
				
				break;
			default:
				break;
			}
			

				if ((checker >= parts_pages[no - 1] && (checker < parts_pages[no]))) {
					if (checker_a == 0 || checker_a == no) {
						switch (bookmark_type) {
						case 1:
							book.b_a_p_t = no;
							break;
						case 2:
							book.b_b_p_t = no;
							break;
						case 3:
							book.b_c_p_t = no;
							break;
						default:
							break;
						}
						bookmark_state = true;
					} else {
						bookmark_state = false;
					}

				} else
					bookmark_state = false;
		
			if(bookmark_state){
				x = (joza_page_no_width+sorah_tanzel_width)+(sorah_tab_book_mark_width)*(bookmark_type-1)  ;
				y = book.parts_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));				
				bookmark_sprite.setColor(r, g, b, .8f);
				bookmark_sprite.setPosition(x, y);
				bookmark_sprite.setOriginCenter();
				bookmark_sprite.setScale(.5f, .95f);
				bookmark_sprite.draw(batch);
			}
		}
		
	}
	class GozaName extends Actor{
		 float width , height , x , y  ;
		 TextureRegion goza_name_texture;
		 Sprite Goza_name_sprite ;
		 int no ;
		 TextActor no_of_surah ;
		float text_y_deferance ;
		public GozaName(int goza_no) {
			// TODO Auto-generated constructor stub
			goza_name_texture = book.get_region("j"+goza_no);
			Goza_name_sprite = new Sprite(goza_name_texture);
			width = joza_name_width ; 
			height = tab_height ;
			Goza_name_sprite.setSize(width, height);
			no = goza_no ;
			text_y_deferance=2*tab_height/3;
			x = (joza_page_no_width+sorah_tanzel_width+ayat_widht)+sorah_tab_book_mark_width*3  ;
			y = book.parts_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			
			no_of_surah = new TextActor(no+"",
					null,
					tab_no_width,
					TextAlign.align_cinter,tab_no_text_scale,x+joza_name_width, text_y_deferance+y);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
		
	// TODO Auto-generated method stub
			x = (joza_page_no_width+sorah_tanzel_width+ayat_widht)+sorah_tab_book_mark_width*3  ;
			y = book.parts_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			
			Goza_name_sprite.setPosition(x, y);
			Goza_name_sprite.setOriginCenter();
			Goza_name_sprite.setScale(.95f);
			Goza_name_sprite.draw(batch);
			no_of_surah.set_position(x+joza_name_width,text_y_deferance+ y);
			no_of_surah.draw(batch, parentAlpha);
		}
	}
	class Pading extends Actor{
		TextureRegion bar ;
		Sprite pading_sprite;
		float x , y , width ,height ; 
		int no ; 
		public Pading(int no_of_me) {
			// TODO Auto-generated constructor stub
			bar = book.loading_texture_atlas.findRegion("loadingbar");
		pading_sprite = new Sprite(bar); 
		pading_sprite.setColor(0,0,0,1);
		width = tab_width ;
		height = tab_pading_height ;
		pading_sprite.setSize(width, height);
		no = no_of_me ;
		}
		@Override
		public void draw(Batch batch, float parentAlpha) {
			// TODO Auto-generated method stub
			super.draw(batch, parentAlpha);
			x =0  ;
			y = book.parts_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no)));
			pading_sprite.setPosition(x, y);
			pading_sprite.draw(batch);
		}
		
	}
	class MakeMadany extends Actor{
		 float width , height , x , y  ;
		 int no ;
		 TextActor surah_page_no ; 

			float text_y_deferance ;
			public MakeMadany(int sorah_no){ 
			// TODO Auto-generated constructor stub
		
			height = tab_height ;
			width = height; 
			
			no = sorah_no ;
			y = book.parts_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			text_y_deferance=2*tab_height/3;
			surah_page_no = new TextActor(""+parts_pages[no-1],
					null,
					joza_page_no_width,
					TextAlign.align_cinter, tab_no_text_scale,0,text_y_deferance+y);
			
		}
		@Override
		public void draw(Batch batch, float parentAlpha) {
			
			
			y = book.parts_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));

			surah_page_no.set_position(0,text_y_deferance+ y);
			surah_page_no.draw(batch, parentAlpha);
	}
	
	
}
	
	
	GozaTap(final int goza_no){
		
	this.add(new GozaName( goza_no)).width(/*gozaname_width+gozatab_book_mark_width*3+tab_no_width*/tab_width).height(tab_height);
	this.add(new MakeMadany( goza_no));
	for (int i=1 ;i<=3 ;i++ )
	{
	this.add(new BookMarks( goza_no , i)); 
	}
	this.addListener(new ActorGestureListener(){

		@Override
		public void tap(InputEvent event, float x, float y, int count, int button) {
			// TODO Auto-generated method stub
			super.tap(event, x, y, count, button);
			
			book.stage_detector=Stages.pages ;
			book.config_input_prossesor(Stages.pages);


			book.current_page = parts_pages[goza_no-1] ;
			book.pages_stage.draw();
			book.pages_draw_stage.draw();
			book.pages_stage.act(Gdx.graphics.getDeltaTime());
			book.pages_scroll_pane.setScrollY((book.current_page-1)*book.screen_height );
			book.snab_to =((book.current_page-1)*book.screen_height ) ; 
			book.update_saved_page_no(book.current_page) ;
			
			
			
			
			
			
		}
	});
	this.row();
	if ( goza_no!=book.ajzaa_no) {
		this.add(new Pading( goza_no)).width(tab_width).height(tab_pading_height);
	}else{
	this.add(new Table()).width(tab_width).height(tab_pading_height);}
}
}
