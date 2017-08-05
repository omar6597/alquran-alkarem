package com.alsiry.alquran;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import java.awt.Color;

import com.alsiry.alquran.book;
import com.alsiry.alquran.GozaTap.GozaName;
import com.alsiry.alquran.TextActor.TextAlign;
import com.alsiry.alquran.book.Stages;

public class SorahTab extends Table {
	public static final float tab_width = Gdx.graphics.getWidth();
	public static final float tab_height = Gdx.graphics.getHeight()/12; 
	final float tab_no_width = .13f*tab_width; 
	final float sorah_page_no_width = .13f*tab_width ;
	final float sorah_name_width =.30f* tab_width; 
	final float sorah_tanzel_width = .1f*tab_width ;
	final float ayat_widht = .13f*tab_width; 
	final float sorah_tab_book_mark_width = .07f*tab_width ;
	final float tab_no_text_scale = tab_height /250 ;
	final float tab_page_text_scale = tab_height /250 ;
	final float ayat_no_text_scale  = tab_height/300 ;
	
	public static final float tab_pading_height = tab_height/100; 
	
	class Ayat extends Actor{
		int no ;
		TextureRegion ayat_texture ; 
		Sprite ayat_sprite ; 
		TextActor ayat_no_text ; 
		float x , y , width , height , text_y_deferance  ; 
		Ayat (int no_of_sorah){
			no = no_of_sorah ; 
			ayat_texture = book.images_textures_atlas.findRegion("ayat"); 
			ayat_sprite = new Sprite(ayat_texture); 
			ayat_sprite .setSize(ayat_widht, tab_height/2);
			x = sorah_page_no_width+sorah_tanzel_width+sorah_tab_book_mark_width*3 ;
			y = book.sorah_scroll_pane.getScrollY()+tab_height/2+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			text_y_deferance=0;
			ayat_no_text = new TextActor(""+no_of_ayat[no-1],
					null,
					ayat_widht,
					TextAlign.align_cinter, ayat_no_text_scale,0,text_y_deferance+y);
			
		}
		@Override
		public void draw(Batch batch, float parentAlpha) {
			// TODO Auto-generated method stub
			x = sorah_page_no_width+sorah_tanzel_width+sorah_tab_book_mark_width*3 ;
			y = book.sorah_scroll_pane.getScrollY()+tab_height/2+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			ayat_sprite.setPosition(x, y);
			ayat_no_text.set_position(x, text_y_deferance+y);
			ayat_sprite.setOriginCenter();
			ayat_sprite.setScale(.7f);
			ayat_sprite.draw(batch);
			ayat_no_text.draw(batch, parentAlpha);
			super.draw(batch, parentAlpha);
		}
		
	}
	class MakeMadany extends Actor{
		 float width , height , x , y  ;
		 TextureRegion goza_name_texture;
		 Sprite Goza_name_sprite ;
		 int no ;
		 TextActor surah_page_no ; 
			boolean suar_state[]={
					true,false,false,false ,
					false,true,true,false , 
					false,true,true,true, 
					false,true,true,true, 
					true,true,true,true,
					true,false,true,false,
					true,true,true,true,
					true,true,true,true,
					false,true,true,true,
					true,true,true,true,
					true,true,true,true,
					true,true,false,false,
					false,true,true,true,
					true,true,false,true,
					false,false,false,false ,
					false,false,false,false ,
					false,false,true,true,
					true,true,true,true,
					true,true,true,false,
					true,true,true,true,
					true,true,true,true,
					true,true,true,true,
					true,true,true,true,
					true,true,true,true,
					true,false,false,true,
					true,true,true,true,
					true,true,true,true,
					true,false,true,true,
					true,true
				}; //88
			
			float text_y_deferance ;
			public MakeMadany(int sorah_no){ 
			// TODO Auto-generated constructor stub
			goza_name_texture = book.get_region(suar_state[sorah_no-1]?"makah":"madinah");
			Goza_name_sprite = new Sprite(goza_name_texture);
			height = tab_height ;
			width = height; 
			Goza_name_sprite.setSize(width, height);
			no = sorah_no ;
			x = sorah_page_no_width+(sorah_tanzel_width-width)/2 ;
			y = book.sorah_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			text_y_deferance=2*tab_height/3;
			surah_page_no = new TextActor(""+surah_page[no-1],
					null,
					sorah_page_no_width,
					TextAlign.align_cinter, tab_no_text_scale,0,text_y_deferance+y);
			
		}
		@Override
		public void draw(Batch batch, float parentAlpha) {
			
			// TODO Auto-generated method stub
			x = sorah_page_no_width+(sorah_tanzel_width-width)/2 ;
			y = book.sorah_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			Goza_name_sprite.setPosition(x, y);
			Goza_name_sprite.setOriginCenter();
			Goza_name_sprite.setScale(.7f);
			Goza_name_sprite.draw(batch);
			surah_page_no.set_position(0,text_y_deferance+ y);
			surah_page_no.draw(batch, parentAlpha);
	}
	
	
}
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
			

			
	
				if ((checker >= surah_page[no - 1] && (checker < surah_page[no]))) {
					if (checker_a == 0 || checker_a == no) {
						switch (bookmark_type) {
						case 1:
							book.b_a_t = no;
							break;
						case 2:
							book.b_b_t = no;
							break;
						case 3:
							book.b_c_t = no;
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
				x = (sorah_page_no_width+sorah_tanzel_width)+(sorah_tab_book_mark_width)*(bookmark_type-1)  ;
				y = book.sorah_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));				
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
			goza_name_texture = book.get_region("s"+goza_no);
			Goza_name_sprite = new Sprite(goza_name_texture);
			width = sorah_name_width ; 
			height = tab_height ;
			Goza_name_sprite.setSize(width, height);
			no = goza_no ;
			text_y_deferance=2*tab_height/3;
			x = (sorah_page_no_width+sorah_tanzel_width+ayat_widht)+sorah_tab_book_mark_width*3  ;
			y = book.sorah_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			
			no_of_surah = new TextActor(no+"",
					null,
					tab_no_width,
					TextAlign.align_cinter,tab_no_text_scale,x+sorah_name_width, text_y_deferance+y);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			// TODO Auto-generated method stub
			x = (sorah_page_no_width+sorah_tanzel_width+ayat_widht)+sorah_tab_book_mark_width*3  ;
			y = book.sorah_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			
			Goza_name_sprite.setPosition(x, y);
			Goza_name_sprite.setOriginCenter();
			Goza_name_sprite.setScale(.95f);
			Goza_name_sprite.draw(batch);
			no_of_surah.set_position(x+sorah_name_width,text_y_deferance+ y);
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
			y = book.sorah_scroll_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no)));
			pading_sprite.setPosition(x, y);
			pading_sprite.draw(batch);
		}
		
	}
		int no_of_ayat[]={
			7,286,200,176,
			120,165,206,75,
			129,109,123,111,
			43,52,99,128,
			111,110,98,135,
			112,78,118,64,
			77,227,93,88,
			69,60,34,30,
			73,54,45,83,
			182,88,75,85,
			54,53,89,59,
			37,35,38,29,
			18,45,60,49,
			62,55,78,96,
			29,22,24,13,
			14,11,11,18,
			12,12,30,52,
			52,44,28,28,
			20,56,40,31,
			50,40,46,42,
			29,19,36,25,
			22,17,19,26,
			30,20,15,21,
			11,8,8,19,
			5,8,8,11,
			11,8,3,9,
			5,4,7,3,
			6,3,5,4,
			5,6
			
		};
		int surah_page[]={
				1,2,50,77,
				106,128,151,177,
				187,208,221,235,
				249,255,262,267,
				282,293,305,312,
				322,332,342,350,
				359,367,377,385,
				396,404,411,415,
				418,428,434,440,
				446,453,458,467,
				477,483,489,496,
				499,502,507,511,
				515,518,520,523,
				526,528,531,534,
				537,542,545,549,
				551,553,554,556,
				558,560,562,564,
				566,568,570,572,
				574,575,577,578,
				580,582,583,585,
				586,587,587,589,
				590,591,591,592,
				593,594,595,595,
				596,596,597,597,
				598,598,599,599,
				600,600,601,601,
				601,602,602,602,
				603,603,603,604,
				604,604,605 //for exeption 
			};
public SorahTab(final int sorah_no) {
	this.add(new GozaName(sorah_no)).width(/*sorah_name_width+sorah_tab_book_mark_width*3+tab_no_width*/tab_width).height(tab_height);
	this.add(new MakeMadany(sorah_no));
	for (int i=1 ;i<=3 ;i++ )
	{
	this.add(new BookMarks(sorah_no , i)); 
	}
	this.add(new Ayat(sorah_no)) ;
	this.addListener(new ActorGestureListener(){

		@Override
		public void tap(InputEvent event, float x, float y, int count, int button) {
			// TODO Auto-generated method stub
			super.tap(event, x, y, count, button);
			book.stage_detector=Stages.pages ;
			book.config_input_prossesor(Stages.pages);


			book.current_page = surah_page[sorah_no-1] ;
			book.pages_stage.draw();
			book.pages_draw_stage.draw();
			book.pages_stage.act(Gdx.graphics.getDeltaTime());
			book.pages_scroll_pane.setScrollY((book.current_page-1)*book.screen_height );
			book.snab_to =((book.current_page-1)*book.screen_height ) ; 
			book.update_saved_page_no(book.current_page) ;
		}
	});
	this.row();
	if (sorah_no!=book.suar_no) {
		this.add(new Pading(sorah_no)).width(tab_width).height(tab_pading_height);
	}else{
	this.add(new Table()).width(tab_width).height(tab_pading_height);}
}
}
