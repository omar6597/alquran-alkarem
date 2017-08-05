package com.alsiry.alquran;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.alsiry.alquran.book;

public class AndroidLauncher extends AndroidApplication implements AndroidComunity{
private String 
part ="«·Ã“¡",
quarter = "—»⁄",
half ="‰’›",
hezp="«·Õ“»",
raqam = "—ﬁ„" ,
three_quatrer = "À·«À… √—»«⁄";
	int important_pages[]={
			/*1,*/5,7,9,11,14,17,19,22,
		24,27,29,32,34,37,39,42,
		44,46,49,51,54,56,59,62,
		64,67,69,72,73,77,79,82,
		84,87,89,92,94,97,100,102,
		104,106,109,112,114,117,119,121,
		124,126,129,132,134,137,140,142,
		144,146,148,151,154,156,158,162,
		164,167,170,173,175,177,179,182,
		184,187,189,192,194,196,199,201,
		204,206,209,212,214,217,219,222,
		224,226,228,231,233,236,238,242,
		244,247,249,252,254,256,259,262,
		264,267,270,272,275,277,280,282,
		284,287,289,292,295,297,299,302,
		304,306,309,312,315,317,319,322,
		324,326,329,332,334,336,339,342,
		344,347,350,352,354,356,359,362,
		364,367,369,371,374,377,379,382,
		384,386,389,392,394,396,399,402,
		404,407,410,413,415,418,420,422,
		425,426,429,431,433,436,439,442,
		444,446,449,451,454,456,459,462,
		464,467,469,472,474,477,479,482,
		484,486,488,491,493,496,499,502,
		505,507,510,513,515,517,519,522,
		524,526,529,531,534,536,539,542,
		544,547,550,553,554,558,560,562,
		564,566,569,572,575,577,579,582,
		585,587,589,591,594,596,599
	}
			;
	
	
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new book(this), config);
	    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	
	
	
	}
	

	@Override
	public void make_toast(final String string ,final boolean long_or_short) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Toast toast = new Toast(AndroidLauncher.this);
				toast.makeText(AndroidLauncher.this, string, long_or_short ?Toast.LENGTH_LONG :toast.LENGTH_SHORT).show();;
			}
		});
	}

	@Override
	public void alert_with_page_no(final int page_no) {
		// TODO Auto-generated method stub
		int place_in_array=0 ; 
		boolean compleat = true ;
		for (int i = 0; i < important_pages.length; i++) {
		if(compleat){
			
			if(page_no==important_pages[i]){
				place_in_array=++i ;
				compleat = false ;
			}
		}
		}
		if(!compleat){
			int what_is_it  = (place_in_array%8) ;
			String string ="" ;
			//int part,half_part ;
			
			
			switch(what_is_it){
case  0:
			string = part+" "+raqam+" ( "+((place_in_array/8)+1)+" )\n"+
						hezp +" "+raqam+" ( "+((place_in_array/4)+1)+" )"
					;
				break ;
case  1:
	string = quarter+" "+hezp +" "+raqam+" ( "+(((place_in_array-1)/4)+1)+" )"
			;
	break ;
case  2:
	string = half+" "+hezp +" "+raqam+" ( "+(((place_in_array-2)/4)+1)+" )"
	;
	break ;
case  3:
	string = three_quatrer+" "+hezp +" "+raqam+" ( "+(((place_in_array-3)/4)+1)+" )"
	;
	break ;
case  4:
	string =hezp +" "+raqam+" ( "+(((place_in_array)/4)+1)+" )"
	;
	break ;
case  5:
	string = quarter+" "+hezp +" "+raqam+" ( "+(((place_in_array-1)/4)+1)+" )"
	;
	break ;
case  6:
	string = half+" "+hezp +" "+raqam+" ( "+(((place_in_array-2)/4)+1)+" )"
	;
	break ;
case  7:
	string = three_quatrer+" "+hezp +" "+raqam+" ( "+(((place_in_array-3)/4)+1)+" )"
	;
	break ;
	
			}

			
			
			
			
			
			final String s =string;
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast toast = new Toast(AndroidLauncher.this);
					toast.makeText(AndroidLauncher.this, s,false ?Toast.LENGTH_LONG :toast.LENGTH_SHORT).show();
				}
			});
		}

	}
}
