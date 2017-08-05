package com.alsiry.alquran;

import com.alsiry.alquran.GameActor;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class book extends ApplicationAdapter {
	
	public enum Stages{
		nothing ,loading , pages , menu ,/*smenu,*/ themset , selectpage ,selectpart , selectsorah, doaa
	}
/**
 * loading variables 
 */
float loading_scroll_max  = 75  ,loading_scroll_value, 
loading_scroll_scrolled,Loading_scroll_scroll_speed =1.5f
; 
//---------------
//book finals 
public final static  int pages_no =604;
public final static  int ajzaa_no = 30 ;
public final static int suar_no = 114 ;
final static int pages_range_to_load =4 ;
final boolean debug_pages =false;
//fjkjkfk
	//android comunity
	static AndroidComunity android ;
	//constractor for android application
	book(AndroidComunity send){
		android = send ; 
	}
	//-----------------------------------
	//constractor for any blatforme 
	book(){}
	//-----------------------------
	//pages Scroll pane variables
	 public static ScrollPane pages_scroll_pane ;
	 private static boolean is_pages_scroll_snab_now  ;
	 static float snab_to;
	private static float delta_total;
	 //-------------------------
	 //parts scroll pane variables 
	 public static ScrollPane parts_scroll_pane ;
	 //
	 //sorah_scroll_pane
	 public static ScrollPane sorah_scroll_pane ;
	 //--------------
	 //select pages scrolle pane 
	 public static ScrollPane select_page_pane ;
	 //
	 //doaa scrolle pane 
	 public static ScrollPane doaa_scroll_pane ;
	 //
	 
	 //menu actors 
	 GameActor screen_totally_a, pages_button ,suar_button, ajzaa_button ,doaa_button, them_button_w,them_button_c ,b_a_button ,
	 b_b_button , b_c_button ,light_slider , light_bar  ; 
	 
	 
	 static int light_value=80 ;
	 
	 //00000000000000
	 //stages           -----------
	static Stage 
	loading_stage ,
	pages_stage , 
	pages_draw_stage ,
	menu_stage ,
	//smenu_stage, 
	themset_stage , 
	selectpage_stage , 
	selectpart_stage ,
	selectsorah_stage ,
	 doaa_stage;	
		
	public static Stages stage_detector ; 
	
//----------------------------------
//sprite batch 	
	SpriteBatch batch;
//---------------
	//fonts 
	public static BitmapFont base_font ;
	
	
	
	//
	//data
	
	static int surah_page[]={
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
	static int parts_pages[]={
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

	//
	//-----------Preferences--------
	 static Preferences preferences;
	String 
	preferences_file_name = "fajk;kfahf";
	static String page_no_key = "dajfhkfjjdhjkf";
	String keymark_a_key = "jkasjgfjkhjkf";
	String keymark_b_key = "kadjfjklhjkfh";
	String keymark_c_key =" nfjdhjfajkhdjkhafjkh";
	String light_strens_key ="fkjldkfhjkhfgjsjkljgkl";
	String is_first_open_key="jdfklhfjkldhfhjkdahf";
	String them_key = "hfjhajkhgjha";
	///------------------------------
	//camera and view port
	static float screen_width;
	static //camera and view port
	float screen_height; 
			public static OrthographicCamera pages_camera; 
		    OrthographicCamera managment_camera; 
			public static Viewport pages_viewport ;
	        Viewport managment_viewport ;
			//----------------------
			//AssetManager
			public static  AssetManager asset ;
			//---------------------------
			
	//texture_atlas		
	public static TextureAtlas loading_texture_atlas ;
	
		TextureRegion loading_bar; 
			GameActor loading_actor ,loading_background_actor;
		TextureRegion logo_texture ;
			GameActor logo_actor ;
	protected static TextureAtlas images_textures_atlas ;
	
	//----------------
	//them and color manager 
		enum Thems{
			colord, classic  ;
		}
	public static Thems them_detector ;		
	
	//--------------
	static GameActor/* screen_totally  plus ,,*/ addba,addbb,addbc,infobacka,infobackb,infobackc;
	static GameActor/* screen_totally  plus ,,*/ surahname;
	static GameActor/* screen_totally  plus ,,*/ jozaname; 
	static TextActor pageno ;
	//pages and bookmarks
	public static int current_page= 1;
	public static int current_surah= 1;
	public static int current_joza = 1;
public static int   bookmark_a_page , bookmark_b_page , bookmark_c_page  ;
public static int b_a_t =0, b_b_t=0, b_c_t=0 ;
public static int b_a_p_t=0 ,b_b_p_t=0 ,b_c_p_t=0 ;
	//----------------
	@Override
	public void create () {
		get_screen_dimention();
	//	android.make_toast("1", false);
		inetilate_camera();
		config_sprite_batch();
		config_AssetManage() ;
	//	android.make_toast("2", false);
		create_loading_texture();
        config_loading_stage();
    //    android.make_toast("3", false);
        
        stage_detector = Stages.nothing ;
	}
	private void get_screen_dimention() {
		screen_width = Gdx.graphics.getWidth();
		screen_height=Gdx.graphics.getHeight();
	}
	private void inetilate_camera() {
		// TODO Auto-generated method stub
		pages_camera = new OrthographicCamera(/*M.game_camera_width, M.game_camera_height*/) ;
		pages_viewport = new FillViewport(screen_width,screen_height, pages_camera);
		pages_camera.position.x = screen_width/2 ;
		pages_camera.position.y = screen_height/2;
		
		managment_camera = new OrthographicCamera() ;
		managment_viewport = new FillViewport(screen_width, screen_height , managment_camera);
		managment_camera.position.x = screen_width /2 ; 
		managment_camera .position.y = screen_height/2 ;
		
		
		
}
	private void config_sprite_batch() {
		// TODO Auto-generated method stub
batch = new SpriteBatch();
	}
	private void config_AssetManage() {
		// TODO Auto-generated method stub
		asset = new AssetManager() ;
	}
	
	private void create_loading_texture() {
				// TODO Auto-generated method stub
		float 
		scroll_width = get_size_in_pixle(loading_scroll_max, false) ,
		scroll_height=get_size_in_pixle(1, false),
		scroll_x = (screen_width - scroll_width)/2 , 
		scroll_y = get_size_in_pixle(10, true) ,
		logo_width =get_size_in_pixle(loading_scroll_max, false),
		logo_height = logo_width , 
		logo_x = (screen_width-logo_width)/2,
		logo_y = (screen_height-logo_height)/2; 
		
		asset.load("introatlas", TextureAtlas.class);
		asset.finishLoading();
		loading_texture_atlas = asset.get("introatlas",TextureAtlas.class);
		loading_bar = loading_texture_atlas.findRegion("loadingbar");
		   loading_actor = new GameActor(
				   loading_bar,
				   scroll_x ,
				   scroll_y,
				   Color.BLACK,
				   scroll_width,
				   scroll_height,true);
		   loading_actor.enable_grade(false);
		   loading_background_actor=new GameActor(
				   loading_bar,
				   scroll_x ,
				   scroll_y,
				   Color.BLACK,
				   scroll_width,
				   scroll_height,true);
		loading_background_actor.update_alpha(.3f);
		loading_background_actor.enable_grade(false);
		logo_texture  = loading_texture_atlas.findRegion("logo");
		logo_actor = new GameActor
				(logo_texture,
						logo_x,
						logo_y,
						null,
						logo_width,
						logo_height);
			logo_actor.update_alpha(1); 
			logo_actor.enable_grade(false);
			}

	private void config_loading_stage() {
	// TODO Auto-generated method stub
	loading_stage = new Stage(managment_viewport, batch);
	loading_stage.addActor(logo_actor);
	loading_stage.addActor(loading_background_actor);
	loading_stage.addActor(loading_actor);
	loading_stage.addListener(new InputListener(){
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			// TODO Auto-generated method stub
			if(keycode == Keys.BACK){
			back_button_down();
			}
			return true;
		}
	});
}

	@Override
	public void render () {
			super.render();
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			switch (stage_detector) {
			case nothing:
			//load resourse inetialy 
				asset.load("imagesatlas", TextureAtlas.class);
				asset.load("fonts/no_font.fnt",BitmapFont.class);
			stage_detector = Stages.loading;
			config_input_prossesor(Stages.loading);
			//pages_camera.update();
			managment_camera.update();
			
			
		//android.make_toast("4", false);
			break;
		case loading:
			loading_scroll_value =(loading_scroll_max*asset.getProgress());
			if(loading_scroll_scrolled+Loading_scroll_scroll_speed<loading_scroll_value){
				loading_scroll_scrolled=(loading_scroll_scrolled + Loading_scroll_scroll_speed) ;
			}
			loading_actor.update_width(get_size_in_pixle(loading_scroll_scrolled, false));
			boolean is_show =false;
			if(asset.update()&&loading_scroll_scrolled==loading_scroll_max-Loading_scroll_scroll_speed){
				loading_actor.set_enable_draw(false);
				loading_background_actor.set_enable_draw(false);
			if(logo_actor.get_alpha()>.02f){
				logo_actor.update_alpha(logo_actor.get_alpha() -.015f);
			}	else{
				is_show = true;
				logo_actor.update_alpha(0);
			}
			if(is_show){ 
				//config uploaded resourses
				images_textures_atlas = asset.get("imagesatlas",TextureAtlas.class);
					config_Preferences();
					config_fonts();
					config_stages();
					
					config_fonts();//
					stage_detector=Stages.pages ;
					config_input_prossesor(Stages.pages);


				
				pages_stage.draw();
				pages_draw_stage.draw();
				pages_stage.act(Gdx.graphics.getDeltaTime());
				pages_scroll_pane.setScrollY((current_page-1)*screen_height );
				snab_to =((current_page-1)*screen_height ) ; 

			}}
			loading_stage.draw();
			break;
		case pages:
		pages_stage.draw();
		pages_draw_stage.draw();
		pages_stage.act(Gdx.graphics.getDeltaTime());
		if(is_pages_scroll_snab_now){
			step_the_snab(); 
		}	   	
			break;
		/*case smenu:
			pages_stage.draw();
			pages_draw_stage.draw();
			pages_stage.act(Gdx.graphics.getDeltaTime());
			if(is_pages_scroll_snab_now){
				step_the_snab(); 
			}	 
			smenu_stage.draw();
			smenu_stage.act(Gdx.graphics.getDeltaTime());
			break ;*/
		case menu:
			
			pages_stage.draw();
			pages_draw_stage.draw();
			pages_stage.act(Gdx.graphics.getDeltaTime());
			if(is_pages_scroll_snab_now){
				step_the_snab(); 
			}	  
			
			
			
menu_stage.draw();
menu_stage.act(Gdx.graphics.getDeltaTime());
			break;
		case selectpage:
			selectpage_stage.draw();
			selectpage_stage.act(Gdx.graphics.getDeltaTime());
			break;
		case selectpart:
			
			selectpart_stage.draw();
			selectpart_stage.act(Gdx.graphics.getDeltaTime());
			
			break;
		case doaa :
			doaa_stage.draw();
			doaa_stage.act(Gdx.graphics.getDeltaTime());
			break ;
		case selectsorah:
			selectsorah_stage.draw();
			selectsorah_stage.act(Gdx.graphics.getDeltaTime());
			break ;
			
		case themset :
			
			break ;
		default:
			break;
		}

	}	
			
	private void config_stages() {
		// TODO Auto-generated method stub
				pages_stage = new Stage(pages_viewport, batch);
				pages_draw_stage = new Stage(pages_viewport, batch) ;
				menu_stage = new Stage(managment_viewport, batch); 
			//	smenu_stage = new Stage(managment_viewport, batch); 
				themset_stage = new Stage(managment_viewport, batch); 
				selectpart_stage = new Stage(managment_viewport, batch); 
				selectsorah_stage = new Stage(managment_viewport, batch); 
				doaa_stage = new Stage(managment_viewport, batch); 
				selectpage_stage = new Stage(managment_viewport, batch); 
				
				
				
				


				create_pages_texture();//
			//	create_pages_menu();//
				create_menu_texture();//
				
				create_selectpage_texture();//
				create_selectsorah_texture();//
				create_selectpart_texture();
				create_doaa_texture(); 
				pages_stage .addListener(new InputListener(){
					@Override
					public boolean keyDown(InputEvent event, int keycode) {
						// TODO Auto-generated method stub
						if(keycode == Keys.BACK){
						back_button_down();
						}
						return true;
					}
				});
				pages_draw_stage .addListener(new InputListener(){
					@Override
					public boolean keyDown(InputEvent event, int keycode) {
						// TODO Auto-generated method stub
						if(keycode == Keys.BACK){
						back_button_down();
						}
						return true;
					}
				});
				menu_stage.addListener(new InputListener(){
					@Override
					public boolean keyDown(InputEvent event, int keycode) {
						// TODO Auto-generated method stub
						if(keycode == Keys.BACK){
						back_button_down();
						}
						return true;
					}
				});

				themset_stage .addListener(new InputListener(){
					@Override
					public boolean keyDown(InputEvent event, int keycode) {
						// TODO Auto-generated method stub
						if(keycode == Keys.BACK){
						back_button_down();
						}
						return true;
					}
				});
				selectpart_stage.addListener(new InputListener(){
					@Override
					public boolean keyDown(InputEvent event, int keycode) {
						// TODO Auto-generated method stub
						if(keycode == Keys.BACK){
						back_button_down();
						}
						return true;
					}
				});
				selectsorah_stage.addListener(new InputListener(){
					@Override
					public boolean keyDown(InputEvent event, int keycode) {
						// TODO Auto-generated method stub
						if(keycode == Keys.BACK){
						back_button_down();
						}
						return true;
					}
				});
				doaa_stage .addListener(new InputListener(){
					@Override
					public boolean keyDown(InputEvent event, int keycode) {
						// TODO Auto-generated method stub
						if(keycode == Keys.BACK){
						back_button_down();
						}
						return true;
					}
				});
				selectpage_stage .addListener(new InputListener(){
					@Override
					public boolean keyDown(InputEvent event, int keycode) {
						// TODO Auto-generated method stub
						if(keycode == Keys.BACK){
						back_button_down();
						}
						return true;
					}
				});
				doaa_stage.addListener(new InputListener(){
					@Override
					public boolean keyDown(InputEvent event, int keycode) {
						// TODO Auto-generated method stub
						if(keycode == Keys.BACK){
						back_button_down();
						}
						return true;
					}
				});
				
			
				
			/*	pages_stage.addActor(new PageSeen(1));*/
	}

			private void create_doaa_texture() {
		// TODO Auto-generated method stub
				
				

				 Table container = new Table();
				 Table table = new Table();
				container.setDebug(debug_pages);
				 table.setDebug(debug_pages);
				 container.setFillParent(true);
				 doaa_scroll_pane  = new ScrollPane(table);

					 container.add(doaa_scroll_pane ).width(screen_width).height(screen_height);
					 ;
					  
					 for (int i = 1 ;i <=2 ;i++){
						 Table tablea = new Table();
						// tablea.setColor((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
						 tablea.setDebug(debug_pages);
						 table.add(tablea).width(screen_width).height(screen_height);
						 table.row();
						 /*
						 selectpart_stage.addActor(new PageSeen(i));*/
						}

						doaa_stage.addActor(container);
						doaa_stage.addActor(new DoaaActor());
					    
					   

				
				
	}
			
			private void create_selectpart_texture() {
		// TODO Auto-generated method stub

				 Table container = new Table();
				 Table table = new Table();
				container.setDebug(debug_pages);
				 table.setDebug(debug_pages);
				 container.setFillParent(true);
					parts_scroll_pane = new ScrollPane(table);

					 container.add(parts_scroll_pane).width(screen_width).height(screen_height);
					 ;
					  
					 
						selectpart_stage.addActor(container);
					    
					    for (int i = 1 ;i <=ajzaa_no ;i++){
							 GozaTap tablea = new GozaTap(i);
							// tablea.setColor((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
							 tablea.setDebug(debug_pages);
							 table.add(tablea).width(GozaTap.tab_width).height(GozaTap.tab_height+GozaTap.tab_pading_height);
							 table.row();
							 /*
							 selectpart_stage.addActor(new PageSeen(i));*/
							}

				
				
	}
			private void create_menu_texture() {
		// TODO Auto-generated method stub
				final float big_button_height = .15f*screen_height; 
				final float big_button_width = big_button_height ;
				final float bookmark_hight = .05f*screen_height;
				final float light_bar_width  = .75f*screen_width ;
				final float light_banal_height = .10f*screen_height; 
				final float light_bar_height = light_banal_height/20 ;
				final float info_panal_height = .07f*screen_height ;
				final float icones_x = 0 ; 
				
				
				
				//plus , addba,addbb,addbc,infoback,pageno,surahname,jozaname;
				final float plus_width = .1f*screen_height ; 
				final float plus_b_height = 0.08f*screen_height ;
				final float plus_b_width = 0.07f*screen_width ;
				final float plus_b_first_x = 0.55f*screen_width; 
				final float plus_b_pading_x = 0.035f*screen_width;
				final float plus_b_y = screen_height - plus_b_height ;
				 //menu actors 
				addba = new GameActor(images_textures_atlas.findRegion("plusbookmark"),
						plus_b_first_x,plus_b_y , null, plus_b_width, plus_b_height);
				addba.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						preferences.putInteger(keymark_a_key, current_page);
						preferences.flush();
						bookmark_a_page = current_page; 
						b_a_t =0;
						b_a_p_t=0 ;
						addba.set_enable_draw(!(bookmark_a_page == current_page));
						
						super.tap(event, x, y, count, button);
					}
				});
				addbb = new GameActor(images_textures_atlas.findRegion("plusbookmark"),
						plus_b_first_x+plus_b_width+plus_b_pading_x, plus_b_y , null, plus_b_width, plus_b_height);
				addbb.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						preferences.putInteger(keymark_b_key, current_page);
						preferences.flush();
						bookmark_b_page = current_page; 
						b_b_t=0;
						b_b_p_t=0 ;
						addbb.set_enable_draw(!(bookmark_b_page == current_page));
					
						super.tap(event, x, y, count, button);
					}
				});
				addbc = new GameActor(images_textures_atlas.findRegion("plusbookmark"),
						plus_b_first_x+2*plus_b_width+2*plus_b_pading_x, plus_b_y , null, plus_b_width, plus_b_height);
				addbc.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						preferences.putInteger(keymark_c_key, current_page);
						preferences.flush();
						bookmark_c_page = current_page; 
						b_c_t=0 ;
						b_c_p_t=0;
						addbc.set_enable_draw(!(bookmark_c_page == current_page));
						super.tap(event, x, y, count, button);
					}
				});
				infobacka = new GameActor(images_textures_atlas.findRegion("pixel"),7*screen_width/8,2*info_panal_height+light_banal_height,Color.WHITE,
						screen_width/8, info_panal_height);
				infobackb = new GameActor(images_textures_atlas.findRegion("pixel"), 2*screen_width/3,1*info_panal_height+light_banal_height,Color.WHITE,
						screen_width/3, info_panal_height);
				infobackc = new GameActor(images_textures_atlas.findRegion("pixel"), 2*screen_width/3,0*info_panal_height+light_banal_height,Color.WHITE,
						screen_width/3, info_panal_height);
				
				
				
				final float text_scale = .5f ;
				
				pageno = new TextActor(current_page+"",Color.BLACK,
						(float)(1*screen_width/8),TextActor.TextAlign.align_cinter, 
						text_scale, 7*screen_width/8, 2.7f*info_panal_height+light_banal_height);
				jozaname = new GameActor(book.get_region("j"+1), 3*screen_width/5,1*info_panal_height+light_banal_height, null, (float)(2*screen_width/5),info_panal_height);

				jozaname.set_scale(.9f,.9f);
				  surahname= new GameActor(book.get_region("s"+1),3*screen_width/5+.12f*(2*screen_width/5),0*info_panal_height+light_banal_height, null, (float)(2*screen_width/5),info_panal_height);
				  surahname.set_scale(.7f, .7f);
				
				
				addba.set_color(255,27,177,1);
				addbb.set_color(27,255,215,1);
				addbc.set_color(255,220,27,1);
//			smenu_stage.addActor(screen_totally);
			//smenu_stage.addActor(plus);

			
				//000000000000000
				screen_totally_a = new GameActor(images_textures_atlas.findRegion("pixel"), big_button_height, light_banal_height, null, screen_width-big_button_height, screen_height-light_banal_height)
						;
						screen_totally_a.addListener(new ActorGestureListener(){

							@Override
							public void tap(InputEvent event, float x, float y, int count, int button) {
								// TODO Auto-generated method stub
								back_button_down();
								super.tap(event, x, y, count, button);
							}
							
						});
						
						screen_totally_a.update_alpha(0.001f);
				
				
				pages_button = new GameActor(images_textures_atlas.findRegion("pagesicon"),
						icones_x,
						big_button_height*4+bookmark_hight*3 +light_banal_height,
						null,big_button_width,
						big_button_height);
				pages_button.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						super.tap(event, x, y, count, button);
						stage_detector = Stages.selectpage ;
						config_input_prossesor(stage_detector);
						selectpage_stage.draw();
						selectpage_stage.act(Gdx.graphics.getDeltaTime());
						select_page_pane.setScrollY(current_page+12<pages_no?current_page*(PageTap.tab_height+PageTap.tab_pading_height):(pages_no-12)*(PageTap.tab_height+PageTap.tab_pading_height));
					}
					
				});
				//--------------
				suar_button = new GameActor(images_textures_atlas.findRegion("suaricon"),
						icones_x,
						big_button_height*3+bookmark_hight*3 +light_banal_height,
						null,big_button_width,
						big_button_height);
				suar_button.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						super.tap(event, x, y, count, button);
						stage_detector = Stages.selectsorah ;
						config_input_prossesor(stage_detector);
						selectsorah_stage.draw();
						selectsorah_stage.act(Gdx.graphics.getDeltaTime());
						sorah_scroll_pane.setScrollY(current_surah+12<suar_no?current_surah*(SorahTab.tab_height+SorahTab.tab_pading_height):(suar_no-12)*(SorahTab.tab_height+SorahTab.tab_pading_height));
						
					}
					
				});
				//----------------
				ajzaa_button = new GameActor(images_textures_atlas.findRegion("partsicon"),
						icones_x,
						big_button_height*2+bookmark_hight*3 +light_banal_height,
						null,big_button_width,
						big_button_height);
				ajzaa_button.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						super.tap(event, x, y, count, button);
						stage_detector = Stages.selectpart ;
						config_input_prossesor(stage_detector);
						selectpart_stage.draw();
						selectpart_stage.act(Gdx.graphics.getDeltaTime());
						parts_scroll_pane.setScrollY(current_joza+12<ajzaa_no?current_joza*(GozaTap.tab_height+GozaTap.tab_pading_height):(ajzaa_no-12)*(GozaTap.tab_height+GozaTap.tab_pading_height));
						
					}
					
				});
				//----------------------------
				doaa_button= new GameActor(images_textures_atlas.findRegion("doaa"),
						icones_x,
						big_button_height+bookmark_hight*3 +light_banal_height,
						null,big_button_width,
						big_button_height);
				doaa_button.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						super.tap(event, x, y, count, button);
						stage_detector = Stages.doaa ;
						config_input_prossesor(stage_detector);
						parts_scroll_pane.setScrollY(0);
					}
					
				});
				//---------------------------
				them_button_w = new GameActor(images_textures_atlas.findRegion("whitepages"),
						icones_x,
						bookmark_hight*3 +light_banal_height,
						null,big_button_width,
						big_button_height);

				them_button_c = new GameActor(images_textures_atlas.findRegion("coloredpages"),
						icones_x,
						bookmark_hight*3 +light_banal_height,
						null,big_button_width,
						big_button_height);
				them_button_c.set_enable_draw(them_detector == Thems.classic?true:false);
				them_button_w.set_enable_draw(them_detector == Thems.colord?true:false);
				them_button_w.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						super.tap(event, x, y, count, button);
						them_button_w.set_enable_draw(false);
						them_button_c.set_enable_draw(true);
						them_detector =Thems.classic ;
						preferences.putInteger(them_key, 0);
						preferences.flush();
					}
					
				});
				them_button_c.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						super.tap(event, x, y, count, button);
						them_button_w.set_enable_draw(true);
						them_button_c.set_enable_draw(false);
						them_detector =Thems.colord ;
						preferences.putInteger(them_key, 1);
						preferences.flush();
					}
					
				});
				//------------------------
				b_a_button = new GameActor(images_textures_atlas.findRegion("vbookmark1"),
						icones_x,
						bookmark_hight*2 +light_banal_height,
						null,big_button_width,bookmark_hight);
				b_a_button.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						super.tap(event, x, y, count, button);
						

						
						/*stage_detector=Stages.pages ;
						config_input_prossesor(Stages.pages);
*/

						current_page = bookmark_a_page ;
						/*pages_stage.draw();
						pages_draw_stage.draw();*//*
						pages_stage.act(Gdx.graphics.getDeltaTime());*/
						pages_scroll_pane.setScrollY((current_page-1)*screen_height );
						snab_to =((current_page-1)*screen_height ) ; 
						update_saved_page_no(current_page) ;	
						refresh_menu();
						
					}
					
				});
				b_a_button.set_scale(1.5f, .65f);
				//----------------------
				b_b_button = new GameActor(images_textures_atlas.findRegion("vbookmark2"),
						icones_x,
						bookmark_hight +light_banal_height,
						null,big_button_width,bookmark_hight);
				b_b_button.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						super.tap(event, x, y, count, button);

						current_page = bookmark_b_page ;
						/*pages_stage.draw();
						pages_draw_stage.draw();*//*
						pages_stage.act(Gdx.graphics.getDeltaTime());*/
						pages_scroll_pane.setScrollY((current_page-1)*screen_height );
						snab_to =((current_page-1)*screen_height ) ; 
						update_saved_page_no(current_page) ;	
						refresh_menu();
					}
					
				});
				b_b_button.set_scale(1.5f, .65f);
				//-------------------------
				b_c_button = new GameActor(images_textures_atlas.findRegion("vbookmark3"),
						icones_x,
						light_banal_height,
						null,big_button_width,bookmark_hight);
				b_c_button.addListener(new ActorGestureListener(){
					@Override
					public void tap(InputEvent event, float x, float y, int count, int button) {
						// TODO Auto-generated method stub
						super.tap(event, x, y, count, button);

						current_page = bookmark_c_page ;
						/*pages_stage.draw();
						pages_draw_stage.draw();*//*
						pages_stage.act(Gdx.graphics.getDeltaTime());*/
						pages_scroll_pane.setScrollY((current_page-1)*screen_height );
						snab_to =((current_page-1)*screen_height ) ; 
						update_saved_page_no(current_page) ;		
						refresh_menu();
					}
					
				});
			b_c_button.set_scale(1.5f, .65f);

				//----------------------
				light_slider = new GameActor(images_textures_atlas.findRegion("brighta"),
					
								
						(light_bar_width*light_value/100)+((screen_width-light_bar_width-light_banal_height)/2)
						,
						0,
						null,light_banal_height,
						light_banal_height);
				light_slider.addListener(new ActorGestureListener(){
					@Override
					public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
						// TODO Auto-generated method stub
						if(light_slider.get_xpos()>=((screen_width-light_bar_width-light_banal_height)/2)&&light_slider.get_xpos()<=screen_width-light_banal_height/2-((screen_width-light_bar_width)/2))
						light_slider.set_position(deltaX+light_slider.get_xpos(), 0);
						
						 if(light_slider.get_xpos()<(screen_width-light_bar_width-light_banal_height)/2){
							light_slider.set_position((screen_width-light_bar_width-light_banal_height)/2, 0);
						}else if(light_slider.get_xpos()>(screen_width-light_banal_height/2-((screen_width-light_bar_width)/2))){
							light_slider.set_position(screen_width-light_banal_height/2-((screen_width-light_bar_width)/2), 0);
							
						}
						 light_value=(int)(100*(
									(light_slider.get_xpos()-
											((screen_width-light_bar_width-light_banal_height)/2))	
											/light_bar_width
											));
						super.pan(event, x, y, deltaX, deltaY);
					}
					@Override
					public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
						// TODO Auto-generated method stub
						light_slider.set_scale(.7f,.7f);
						
						super.touchDown(event, x, y, pointer, button);
					}
					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
						// TODO Auto-generated method stub
						light_slider.set_scale(.5f,.5f);
						
						
						preferences.putInteger(light_strens_key, light_value);
						
						preferences.flush();
						
						
						super.touchUp(event, x, y, pointer, button);
					}
					 
					
				});
				light_slider.set_scale(.5f,.5f);
				//--------
				light_bar = new GameActor(images_textures_atlas.findRegion("pixel"),
						(screen_width-light_bar_width)/2,
						(light_banal_height-light_bar_height)/2,
						null,light_bar_width,
						light_bar_height);
				/*light_bar.set_scale(.9f,.9f);*/
				
				//
				light_slider.set_color(145,139, 106,1);
				light_bar.set_color(19, 19, 19, 1);
				b_a_button.set_color(255,27,177,1);
				b_b_button.set_color(27,255,215,1);
				b_c_button.set_color(255,220,27,1);
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				menu_stage.addActor(screen_totally_a);
				
				
				
				menu_stage.addActor(addba);
				menu_stage.addActor(addbb);
				menu_stage.addActor(addbc);	
				//menu_stage.addActor(infobacka);
				//menu_stage.addActor(infobackb);
				//menu_stage.addActor(infobackc);
				menu_stage.addActor(pageno);
				menu_stage.addActor(surahname);
				menu_stage.addActor(jozaname);
				
				
				menu_stage.addActor(pages_button);
				menu_stage.addActor(suar_button); 
				menu_stage.addActor(ajzaa_button);
				menu_stage.addActor(doaa_button);
				menu_stage.addActor(them_button_c);
				menu_stage.addActor(them_button_w);
				menu_stage.addActor(b_a_button);
				menu_stage.addActor(b_b_button);
				menu_stage.addActor(b_c_button);
				menu_stage.addActor(light_bar);
				menu_stage.addActor(light_slider);
			}
			private void config_fonts() {
		// TODO Auto-generated method stub
		base_font = asset.get("fonts/no_font.fnt",BitmapFont.class); 
	}
			private void create_selectsorah_texture() {

				 Table container = new Table();
				 Table table = new Table();
				container.setDebug(debug_pages);
				 table.setDebug(debug_pages);
				 container.setFillParent(true);
					sorah_scroll_pane = new ScrollPane(table);

					 container.add(sorah_scroll_pane).width(screen_width).height(screen_height);
					 ;
					  
					 
						selectsorah_stage.addActor(container);
					    
					    for (int i = 1 ;i <=suar_no ;i++){
							 SorahTab tablea = new SorahTab(i);
							// tablea.setColor((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
							 tablea.setDebug(debug_pages);
							 table.add(tablea).width(SorahTab.tab_width).height(SorahTab.tab_height+SorahTab.tab_pading_height);
							 table.row();
							 /*
							 selectpart_stage.addActor(new PageSeen(i));*/
							}

		
	}
			private void create_selectpage_texture() {
		// TODO Auto-generated method stub

				 Table container = new Table();
				 Table table = new Table();
				container.setDebug(debug_pages);
				 table.setDebug(debug_pages);
				 container.setFillParent(true);
					select_page_pane = new ScrollPane(table);

					 container.add(select_page_pane).width(screen_width).height(screen_height);
					 ;
					  
					 
						selectpage_stage.addActor(container);
					    
					    for (int i = 1 ;i <=pages_no ;i++){
							 PageTap tablea = new PageTap(i);
							// tablea.setColor((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
							 tablea.setDebug(debug_pages);
							 table.add(tablea).width(PageTap.tab_width).height(PageTap.tab_height+PageTap.tab_pading_height);
							 table.row();
							 /*
							 selectpart_stage.addActor(new PageSeen(i));*/
							}

				
		
	}
			public static void set_is_now_scrolling (boolean state ,float  scroling_to){
				is_pages_scroll_snab_now = state ;
				snab_to =scroling_to<0 ?snab_to:scroling_to ;
			}
			private void create_pages_texture() {
		// TODO Auto-generated method stub
				
				 Table container = new Table();
				 Table table = new Table();
				container.setDebug(debug_pages);
				 table.setDebug(debug_pages);
				 container.setFillParent(true);
	 
				  pages_scroll_pane = new ScrollPane(table);

				 pages_scroll_pane.layout();
				 container.add(pages_scroll_pane).width(screen_width).height(screen_height);
				 ;
				 pages_scroll_pane.setTouchable(Touchable.enabled);
				 pages_scroll_pane.setBounds(0, 0, screen_width, screen_height);
				  
				    container.setBounds(0,0,screen_width,screen_height);
				    container.setTouchable(Touchable.enabled);
				    
				    pages_stage.addActor(container);
				    
				    for (int i = 1 ;i <=pages_no ;i++){
						 Table tablea = new Table();
						// tablea.setColor((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
						 tablea.setDebug(debug_pages);
						 table.add(tablea).width(screen_width).height(screen_height);
						 table.row();
						 pages_draw_stage.addActor(new PageSeen(i));
						}
				  
	}
			
			private void config_Preferences() {
				// TODO Auto-generated method stub
				preferences = Gdx.app.getPreferences(preferences_file_name);
					if (preferences.getBoolean(is_first_open_key)!=true) {
						//first time so creat the keys
						preferences.putInteger(page_no_key, 1);
						preferences.putInteger(keymark_a_key, 1);
						preferences.putInteger(keymark_b_key, 1);
						preferences.putInteger(keymark_c_key, 1);
						preferences.putInteger(them_key, 0);
						preferences.putInteger(light_strens_key, light_value);
						preferences.putBoolean(is_first_open_key, true);
						preferences.flush();
					}
					current_page = preferences.getInteger(page_no_key);
					bookmark_a_page = preferences.getInteger(keymark_a_key); 
					bookmark_b_page = preferences.getInteger(keymark_b_key);
					bookmark_c_page = preferences.getInteger(keymark_c_key) ;
					light_value = preferences.getInteger(light_strens_key);
					them_detector = preferences.getInteger(them_key)==0?Thems.classic:Thems.colord; 
					
			}
	
//button_back sit
		public void back_button_down(){
		switch (stage_detector) {
		case doaa:
			stage_detector = Stages.menu ;
			config_input_prossesor(stage_detector);
			break;
		case loading:
		
			break;
		case pages:
			Gdx.app.exit();
			break;
		case  selectpage :
			stage_detector = Stages.menu ;
			config_input_prossesor(stage_detector);
			break;
		case selectpart:
			stage_detector = Stages.menu ;
			config_input_prossesor(stage_detector);
			break;
		case selectsorah:
			stage_detector = Stages.menu ;
			config_input_prossesor(stage_detector);
			break ; 
		case menu :
			stage_detector = Stages.pages ;
			config_input_prossesor(stage_detector);
			break ; 
	/*	case smenu :
			stage_detector = Stages.pages ;
			config_input_prossesor(stage_detector);
			break ;*/
		case themset :

			break ;
		default:
			break;
		}
	}
	
//8900000000000000000000000000000000000	
	//dispose part
	@Override
	public void dispose () {
		batch.dispose();
		loading_texture_atlas.dispose();
		loading_stage.dispose();
		doaa_stage.dispose();
		loading_stage.dispose();
		menu_stage.dispose();
		//smenu_stage.dispose();
		selectpage_stage.dispose();
		selectpart_stage.dispose();
		selectsorah_stage.dispose();
		pages_draw_stage.dispose();
		pages_stage.dispose();
		images_textures_atlas.dispose();
		
		
	}
	//------------gasgggggggggggggggggggggggggggg
	//textures function 
	public  static TextureRegion get_region (String texture_region_name){
		return images_textures_atlas.findRegion(texture_region_name);
	}
	
	
	//000000000000000
	//input prosesor part 
	static void config_input_prossesor(Stages stage) {
		// TODO Auto-generated method stub
		Gdx.input.setCatchBackKey(true);
		switch (stage) {
		case doaa:
			Gdx.input.setInputProcessor(null);
			Gdx.input.setInputProcessor(doaa_stage);
			break;
		case loading:
			Gdx.input.setInputProcessor(null);
			Gdx.input.setInputProcessor(loading_stage);
			
			break;
		case pages:
			Gdx.input.setInputProcessor(null);
			Gdx.input.setCatchBackKey(false);
			Gdx.input.setInputProcessor(new GestureDetector(new ScrollListner(){
				
				@Override
				public boolean pan(float x, float y, float deltaX, float deltaY) {
					pages_scroll_pane.setScrollY(pages_scroll_pane.getScrollY()-deltaY);
					if(is_pages_scroll_snab_now){
						current_page=(pages_scroll_pane.getScrollY()%screen_height)/screen_height>.5?
								(int)(pages_scroll_pane.getScrollY()/screen_height)+1 :
									(int)(pages_scroll_pane.getScrollY()/screen_height) ;
					update_saved_page_no(current_page) ;					
					}
					return super.pan(x, y, deltaX, deltaY);
				}
				@Override
				public boolean touchDown(float x, float y, int pointer, int button) {
					// TODO Auto-generated method stub
					delta_total = y; 
					book.set_is_now_scrolling(false,-1);
					return super.touchDown(x, y, pointer, button);
					
				}
				@Override
				public boolean tap(float x, float y, int count, int button) {

					book.set_is_now_scrolling(true,-1);
					final float light_banal_height = .11f*screen_height; 
					final float info_panal_height = .07f*screen_height ;
					
					pageno.update_text(""+current_page);
				
					
					
					
					int surah_no =1 ; 
					boolean compleat = true ;
						for (int i = 0; i < surah_page.length-1; i++) {
							
							if(compleat){
							if (current_page == surah_page[i]){
								surah_no = ++i ; 
								compleat=false;
							}else if (current_page>=surah_page[i]&&current_page<surah_page[i+1]){
								surah_no = ++i ; 
								compleat=false;
							}}
						}
						
						current_surah = surah_no; 
						surahname.remove(); 
						surahname = new GameActor(book.get_region("s"+surah_no),3*screen_width/5+.12f*(2*screen_width/5),0*info_panal_height+light_banal_height, null, (float)(2*screen_width/5),info_panal_height);
						
						
						
						
						surahname.set_scale(.7f, .7f);
						menu_stage.addActor(surahname);
					
					
						
						int part_no =1 ; 
						 compleat = true ;
							for (int i = 0; i < parts_pages.length-1; i++) {
								
								if(compleat){
								if (current_page == parts_pages[i]){
									part_no = ++i ; 
									compleat=false;
								}else if (current_page>=parts_pages[i]&&current_page<parts_pages[i+1]){
									part_no = ++i ; 
									compleat=false;
								}}
							}
							current_joza = part_no;
						jozaname.remove();
							jozaname = new GameActor(book.get_region("j"+part_no), 3*screen_width/5,1*info_panal_height+light_banal_height, null, (float)(2*screen_width/5),info_panal_height);

							jozaname.set_scale(.9f,.9f);
							
							menu_stage.addActor(jozaname);
							
							
					
					stage_detector = Stages.menu ;
					config_input_prossesor(Stages.menu);
					addba.set_enable_draw(!(bookmark_a_page == current_page));
					addbb.set_enable_draw(!(bookmark_b_page == current_page));
					addbc.set_enable_draw(!(bookmark_c_page == current_page));
					return super.tap(x, y, count, button);
				}
@Override
				public boolean panStop(float x, float y, int pointer, int button) {
					// TODO Auto-generated method stub
					delta_total -=y; 
					float scrolling_factor =(((pages_scroll_pane.getScrollY()%screen_height)/screen_height>10))?.25f:.1f;
					float go_to = 0 ;
					boolean up_or_down = delta_total>0?true :false;
					if(Math.abs(delta_total)>scrolling_factor*screen_height){
						if(up_or_down){
							go_to = (((current_page)<pages_no/*604*/?++current_page:current_page)-1)*screen_height;
						}
						else{
							go_to = (((current_page)>1/*604*/?--current_page:current_page)-1)*screen_height;
						}
					}
					else{
						go_to = (current_page-1)*screen_height;
					}
				book.set_is_now_scrolling(true,go_to) ;
				update_saved_page_no(current_page) ;			
				android.alert_with_page_no(current_page);
					return super.panStop(x, y, pointer, button);
					
				}	
			}));
			break;
		case  selectpage :

			Gdx.input.setInputProcessor(null);
			Gdx.input.setInputProcessor(selectpage_stage);
			break;
		case selectpart:
			Gdx.input.setInputProcessor(null);
			Gdx.input.setInputProcessor(selectpart_stage);
			break;
		case selectsorah:
			Gdx.input.setInputProcessor(null);
			Gdx.input.setInputProcessor(selectsorah_stage);
			break ; 
		case menu :
			Gdx.input.setInputProcessor(null);
			Gdx.input.setInputProcessor(menu_stage);
			break ; 
/*		case smenu :
			Gdx.input.setInputProcessor(null);
			Gdx.input.setInputProcessor(smenu_stage);
			break;*/
		case themset :
			Gdx.input.setInputProcessor(null);
			Gdx.input.setInputProcessor(themset_stage);
			break ;
		default:
			break;
		}
		
	}
	//------------------------------
	//dimention calculator
	//dimentions calculations functions _________________________________________________
		private float get_size_in_pixle(float retio , boolean height_or_width){
			if (height_or_width)
			{
				return ((retio/100)*screen_height); 
				}
			else
			{
				return ((retio/100)*screen_width);	
				}
			}
		
		//end dimentions calculations functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//scroll snab part
		private void refresh_menu(){
			final float light_banal_height = .11f*screen_height; 
			final float info_panal_height = .07f*screen_height ;
			
			pageno.update_text(""+current_page);
		
			
			
			
			int surah_no =1 ; 
			boolean compleat = true ;
				for (int i = 0; i < surah_page.length-1; i++) {
					
					if(compleat){
					if (current_page == surah_page[i]){
						surah_no = ++i ; 
						compleat=false;
					}else if (current_page>=surah_page[i]&&current_page<surah_page[i+1]){
						surah_no = ++i ; 
						compleat=false;
					}}
				}
				
				current_surah = surah_no; 
				
				surahname.remove(); 
				surahname = new GameActor(book.get_region("s"+surah_no),3*screen_width/5+.12f*(2*screen_width/5),0*info_panal_height+light_banal_height, null, (float)(2*screen_width/5),info_panal_height);
				
				
				
				
				surahname.set_scale(.7f, .7f);
				menu_stage.addActor(surahname);
			
			
				
				int part_no =1 ; 
				 compleat = true ;
					for (int i = 0; i < parts_pages.length-1; i++) {
						
						if(compleat){
						if (current_page == parts_pages[i]){
							part_no = ++i ; 
							compleat=false;
						}else if (current_page>=parts_pages[i]&&current_page<parts_pages[i+1]){
							part_no = ++i ; 
							compleat=false;
						}}
					}

					current_joza = part_no;
				jozaname.remove();
					jozaname = new GameActor(book.get_region("j"+part_no), 3*screen_width/5,1*info_panal_height+light_banal_height, null, (float)(2*screen_width/5),info_panal_height);

					jozaname.set_scale(.9f,.9f);
					
					menu_stage.addActor(jozaname);
					
					
			
			stage_detector = Stages.menu ;
			config_input_prossesor(Stages.menu);
			addba.set_enable_draw(!(bookmark_a_page == current_page));
			addbb.set_enable_draw(!(bookmark_b_page == current_page));
			addbc.set_enable_draw(!(bookmark_c_page == current_page));
		}
		private void step_the_snab() {
			// TODO Auto-generated method stub
					if(Math.abs(pages_scroll_pane.getScrollY()-snab_to)>Gdx.graphics.getHeight()/500)
					pages_scroll_pane.setScrollY(pages_scroll_pane.getScrollY()+
							(snab_to-pages_scroll_pane.getScrollY())/15
							);
					else if(Math.abs(pages_scroll_pane.getScrollY()-snab_to)<Gdx.graphics.getHeight()/500){
						pages_scroll_pane.setScrollY(snab_to); 
						is_pages_scroll_snab_now = false ;
						addba.set_enable_draw(!(bookmark_a_page == current_page));
						addbb.set_enable_draw(!(bookmark_b_page == current_page));
						addbc.set_enable_draw(!(bookmark_c_page == current_page));
					}
			
		}
		//0--------------------------------
		//pages manager 
		static void update_saved_page_no(int current_page) {
			// TODO Auto-generated method stub
			preferences.putInteger(page_no_key, current_page);
			preferences.flush();
		}
		//------------------------
}