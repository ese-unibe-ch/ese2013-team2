package ch.unibe.unisportbern.views.friends;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.CustomOnClickListener;
import ch.unibe.unisportbern.support.UserManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * This class is the dialog that is shown when one wants to search for a user. 
 * @author Thomas Steinmann
 *
 */

public class SearchFriendDialog extends DialogFragment {

	private View view;
	private UserManager manager;
	

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();
		view = inflater.inflate(R.layout.search_friend_dialog_layout, null);

		builder.setView(view);

		builder.setPositiveButton("Ok", new CustomOnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO: 
			}
		});

		manager = new UserManager(getActivity());
		
		Button searchButton = (Button) view.findViewById(R.id.buttonSearchFriend);
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ListView list = (ListView) view.findViewById(R.id.listViewSearchFriendResult);
				EditText et = (EditText) view.findViewById(R.id.editTextSearchFriendName);
				
				CustomFriendResultListAdapter adapter =
						new CustomFriendResultListAdapter(getActivity(), new String[0], SearchFriendDialog.this.getDialog(), (FriendsFragment)getTargetFragment());
				
				manager.orderSearchOtherUser(et.getText().toString());
				manager.createView(adapter, list);
				
				TextView tv = (TextView) view.findViewById(R.id.textViewResult);
				tv.setVisibility(View.VISIBLE);
				
			}
		});
		
		return builder.create();
	}

}
