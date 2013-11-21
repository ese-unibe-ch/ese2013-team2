package ch.unibe.unisportbern.parse;

import java.util.List;

import android.net.ParseException;

import ch.unibe.unisportbern.support.DBMethodes;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ParseMethodes {
	
	public ParseMethodes(){
		
	}
	
	public void signingUp(String username, String password){
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		 
		user.signUpInBackground(new SignUpCallback() {
		  public void done(ParseException e) {
		    if (e == null) {
		      // Hooray! Let them use the app now.		    	
		    } else {
		      // Sign up didn't succeed. Look at the ParseException
		      // to figure out what went wrong
		    }
		  }

		@Override
		public void done(com.parse.ParseException e) {
			// TODO Auto-generated method stub
			
		}
		});
	}
	
	public String getUsername(){
		return null;
	}

}
