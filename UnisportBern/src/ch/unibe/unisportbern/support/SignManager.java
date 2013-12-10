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
	private String newPassword;
	
	public SignManager(Context context){
		this.context =  context;
		parse = new ParseMethodes(context);
		parse.addObserver(this);
	}
	
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(parse.isInvalid()==true || username.isEmpty()|| newPassword.isEmpty()){
			dialog = new SignUpDialog();
			dialog.show(((Activity) context).getFragmentManager(), "Welcome Dialog");
			Toast.makeText(context, R.string.ToastInvalidUsername, Toast.LENGTH_LONG).show();
		}
		
	}
	
	public void isDuplicateUsername(String newUsername, String password){
		username = newUsername;
		newPassword = password;
		parse.isDuplicate(newUsername);
	}

}
