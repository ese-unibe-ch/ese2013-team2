package ch.unibe.unisportbern.support;

import ch.unibe.unisportbern.views.dialogs.StandardMessageDialog;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

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
			
			Bundle bundle = new Bundle();
			bundle.putString("title", "No Network Connection");
			bundle.putString("message", "Keine Netzwerkverbindung");
			
			dialog.setArguments(bundle);
			dialog.show(((Activity)context).getFragmentManager(), "Atention");
			return false;
		}
	}

}
