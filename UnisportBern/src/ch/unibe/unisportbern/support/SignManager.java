package ch.unibe.unisportbern.support;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.views.dialogs.SignUpLogInDialog;

/**
 * is responsible for managing the sign ups and logging in into the application
 * 
 * @author Karan Sethi
 */

public class SignManager implements Observer {
	
	private SignUpLogInDialog dialog;
	private ParseMethodes parse;
	private Context context;
	private String username;
	private String password;
	
	public SignManager(Context context){
		this.context =  context;
		parse = new ParseMethodes(context);
		parse.addObserver(this);
	}
	
	/**
	 * This method looks for the username in the cache.
	 * @return if true the the login will be done automatically else register.
	 *
	 */
	
	
	public boolean automaticLogin(){
		return parse.automaticLogin();
	}
	
	public void logInOrSignUp(String username, String password){
		parse.loggingIn(username, password);
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		if(parse.isInvalid())
			restartDialog();
	}
	
	/**
	 * looks if the username and password are empty and restart the dialog. or saves them and calls the method to sign up or log in.
	 * @param newUsername username given into the register dialog
	 * @param newPassword given
	 */
	public void saveAndlogIn(String newUsername, String newPassword){
		username = newUsername;
		password = newPassword;
		if(username.isEmpty() || password.isEmpty())
		restartDialog();
		else
			this.logInOrSignUp(newUsername, newPassword);
	}
	
	private void restartDialog(){
		dialog = new SignUpLogInDialog();
		dialog.show(((Activity) context).getFragmentManager(), "Welcome Dialog");
		Toast.makeText(context, R.string.ToastInvalidUsername, Toast.LENGTH_LONG).show();
		
	}

}
