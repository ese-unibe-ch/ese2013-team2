package ch.unibe.unisportbern.views.details.reminder;

	


import java.util.ArrayList;
import ch.unibe.unisportbern.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * This class is responsible for the selection of the options for the time of the notification.
 * @author Karan
 *
 */
	public class OptionsActivity extends Activity {
		
		private RadioGroup timeOptions;
		private Button confirm;
		private RadioButton radioButtonSelected;
		private ArrayList <Integer> timeElements;
		private ArrayList <Integer> dateElements;
		private String sportName;
		private String startTime;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.radio);
			Intent i =  getIntent();
			i.getStringExtra("date");
			timeElements  = this.splitTimeElements(i.getStringExtra("time"));
			dateElements = this.splitTimeElements(i.getStringExtra("date"));
			sportName = i.getStringExtra("sports");
			startTime = i.getStringExtra("time");
			addListenerOnButton();
		}
		
		  public void addListenerOnButton() {
			timeOptions = (RadioGroup) findViewById(R.id.radioSex);
			confirm = (Button) findViewById(R.id.btnDisplay);
			confirm.setOnClickListener(new OnClickListener() {
				
					@Override
					public void onClick(View v) {
						
					    // get selected radio button from radioGroup
						int selectedId = timeOptions.getCheckedRadioButtonId();
			 
						// find the radiobutton by returned id
					    radioButtonSelected = (RadioButton) findViewById(selectedId);
						Toast.makeText(OptionsActivity.this,
						   radioButtonSelected.getText(), Toast.LENGTH_SHORT).show();
						
						//boolean checked = ((RadioButton) v).isChecked();
						int radioButtonID = timeOptions.getCheckedRadioButtonId();
						View radioButton = timeOptions.findViewById(radioButtonID);
						int idx = timeOptions.indexOfChild(radioButton);
						int min=0;
						switch(idx) {
						case 0: min= 2;
		                        break;
		                case 1: min = 90;
		                    
		                        break;
		                case 2: min = 60;
		                    
		                        break;
		                case 3: min = 30;
		                	break;
		                }
						Intent intent = new Intent (OptionsActivity.this, BootReceiver.class);
						intent.putExtra("radioTime", min);
						intent.putIntegerArrayListExtra("dateEl", dateElements);
						intent.putIntegerArrayListExtra("timeEl", timeElements);
						intent.putExtra("time", startTime);
						intent.putExtra("sports", sportName);
						sendBroadcast(intent);
						finish();
					}
			}); 
	   }
		  private ArrayList<Integer> splitTimeElements(String element){
			  String[] temp;
			  String startTime = element.substring(0, element.length());
			  System.out.println("");
			  String delimiter = "\\.";
			  temp = startTime.split(delimiter);
			  ArrayList<Integer> elementList = new ArrayList<Integer>();
			  for(int i =0; i < temp.length ; i++){
				  if(temp[i].substring(0,1).equals("0"))
					  temp[i]= temp[i].substring(1);
				 elementList.add(Integer.parseInt(temp[i])) ;  
			  }
			  return elementList;
		}
	}



