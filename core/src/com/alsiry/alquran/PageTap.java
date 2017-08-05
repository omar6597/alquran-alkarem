package com.alsiry.alquran;

import com.alsiry.alquran.GozaTap.BookMarks;
import com.alsiry.alquran.GozaTap.GozaName;
import com.alsiry.alquran.GozaTap.MakeMadany;
import com.alsiry.alquran.GozaTap.Pading;
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

public class PageTap extends  Table{
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

	class MakeMadany extends Actor{
		 float width , height , x , y  ;
		 TextureRegion goza_name_texture;
		 Sprite Goza_name_sprite ;
		 int no ;
		 TextActor surah_page_no ; 
			
			float text_y_deferance ;
			public MakeMadany(int page_no  , boolean state ){ 
			// TODO Auto-generated constructor stub
			goza_name_texture = book.get_region(state?"makah":"madinah");
			Goza_name_sprite = new Sprite(goza_name_texture);
			height = tab_height ;
			width = height; 
			Goza_name_sprite.setSize(width, height);
			no = page_no ;
			x = sorah_page_no_width+(sorah_tanzel_width-width)/2 ;
			y = book.select_page_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			text_y_deferance=2*tab_height/3;
			surah_page_no = new TextActor(""+no,
					null,
					sorah_page_no_width,
					TextAlign.align_cinter, tab_no_text_scale,0,text_y_deferance+y);
			
		}
		@Override
		public void draw(Batch batch, float parentAlpha) {
			
			// TODO Auto-generated method stub
			x = sorah_page_no_width+(sorah_tanzel_width-width)/2 ;
			y = book.select_page_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
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
			
	
				if ((checker == no )) {
					bookmark_state = true;

				} else
					bookmark_state = false;
		
			if(bookmark_state){
				x = (sorah_page_no_width+sorah_tanzel_width)+(sorah_tab_book_mark_width)*(bookmark_type-1)  ;
				y = book.select_page_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));				
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
		public GozaName(int goza_no , int page_no) {
			// TODO Auto-generated constructor stub
			goza_name_texture = book.images_textures_atlas.findRegion("s"+goza_no);
			Goza_name_sprite = new Sprite(goza_name_texture);
			width = sorah_name_width ; 
			height = tab_height ;
			Goza_name_sprite.setSize(width, height);
			no = page_no ;
			text_y_deferance=2*tab_height/3;
			x = (sorah_page_no_width+sorah_tanzel_width+ayat_widht)+sorah_tab_book_mark_width*3  ;
			y = book.select_page_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			
			no_of_surah = new TextActor(page_no+"",
					null,
					tab_no_width,
					TextAlign.align_cinter,tab_no_text_scale,x+sorah_name_width, text_y_deferance+y);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			// TODO Auto-generated method stub
			x = (sorah_page_no_width+sorah_tanzel_width+ayat_widht)+sorah_tab_book_mark_width*3  ;
			y = book.select_page_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no-1)));
			
			Goza_name_sprite.setPosition(x, y);
			Goza_name_sprite.setOriginCenter();
			Goza_name_sprite.setScale(.9f,.7f);
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
			y = book.select_page_pane.getScrollY()+Gdx.graphics.getHeight()-((tab_height*no+tab_pading_height*(no)));
			pading_sprite.setPosition(x, y);
			pading_sprite.draw(batch);
		}
		
	}
	

	public PageTap(final int page_no) {
		int surah_no =1 ; 
	boolean compleat = true ;
		for (int i = 0; i < surah_page.length-1; i++) {
			
			if(compleat){
			if (page_no == surah_page[i]){
				surah_no = i ; 
				compleat=false;
			}else if (page_no>=surah_page[i]&&page_no<surah_page[i+1]){
				surah_no = i ; 
				compleat=false;
			}}
		}
	boolean maky_or_madany  = suar_state[surah_no] ;
	
	
	this.add(new GozaName(surah_no+1 , page_no)).width(/*gozaname_width+gozatab_book_mark_width*3+tab_no_width*/tab_width).height(tab_height);
	this.add(new MakeMadany( page_no,maky_or_madany));
	for (int i=1 ;i<=3 ;i++ )
	{
	this.add(new BookMarks(	page_no , i)); 
	}
	this.addListener(new ActorGestureListener(){

		@Override
		public void tap(InputEvent event, float x, float y, int count, int button) {
			// TODO Auto-generated method stub
			super.tap(event, x, y, count, button);
			book.stage_detector=Stages.pages ;
			book.config_input_prossesor(Stages.pages);


			book.current_page = page_no ;
			book.pages_stage.draw();
			book.pages_draw_stage.draw();
			book.pages_stage.act(Gdx.graphics.getDeltaTime());
			book.pages_scroll_pane.setScrollY((book.current_page-1)*book.screen_height );
			book.snab_to =((book.current_page-1)*book.screen_height ) ; 
			book.update_saved_page_no(book.current_page) ;
		}
	});
	this.row();
	if ( page_no!=book.pages_no) {
		this.add(new Pading( page_no)).width(tab_width).height(tab_pading_height);
	}else{
	this.add(new Table()).width(tab_width).height(tab_pading_height);}
}
}