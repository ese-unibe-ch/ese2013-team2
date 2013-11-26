package ch.unibe.unisportbern.support;

import ch.unibe.unisportbern.views.dialogs.StandardMessageDialog;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {
	
	Context context;
	
	public Network(Context context){
		this.context = context;
	}
	
	public boolean isOnline() {
		ConnectivityManager cm =
		        (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		 
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()) return true;
		else{
			StandardMessageDialog dialog = new StandardMessageDialog();
			dialog.show(((Activity)context).getFragmentManager(), "Atention!", "No Network Connection!", "Keine Netzwerkverbindung");
			return false;
		}
	}

}
