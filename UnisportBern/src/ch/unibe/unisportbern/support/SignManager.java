package ch.unibe.unisportbern.support;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.views.dialogs.SignUpDialog;

public class SignManager implements Observer {
	
	private SignUpDialog dialog;
	private ParseMethodes parse;
	private Context context;
	private String username;
	private String password;
	
	public SignManager(Context context){
		this.context =  context;
		parse = new ParseMethodes(context);
		parse.addObserver(this);
	}
	
	public void signup(String username, String password){
		parse.signingUp(username,password);
	}
	
	public boolean automaticLogin(){
		return parse.automaticLogin();
	}
	
	public void logIn(String username, String password){
		parse.loggingIn(username, password);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(parse.isInvalid()==true || username.isEmpty()|| password.isEmpty()){
			dialog = new SignUpDialog();
			dialog.show(((Activity) context).getFragmentManager(), "Welcome Dialog");
			Toast.makeText(context, R.string.ToastInvalidUsername, Toast.LENGTH_LONG).show();
		}
		
		else if (parse.isWrongLogin()==true|| username.isEmpty()||password.isEmpty()){
			dialog = new SignUpDialog();
			dialog.show(((Activity) context).getFragmentManager(), "Welcome Dialog");
			Toast.makeText(context, R.string.ToastInvalidUsername, Toast.LENGTH_LONG).show();
		}
	}
	
	public void saveInManger(String newUsername, String newPassword){
		username = newUsername;
		password = newPassword;
	}
	
	public void isDuplicateUsername(String newUsername){
		parse.isDuplicate(newUsername);
	}

}
