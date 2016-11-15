package com.main.calc;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

import javax.xml.transform.stream.StreamSource;

import java.util.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.main.exceptions.TerminatedException;
import com.main.math.*;


public class MainActivity extends Activity {

	private EditText _txt = null;
	private EditText _txtRes = null;
	private CheckBox _EngineeringMode = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _txt = (EditText)findViewById(R.id.editText1);
        _txt.setText("GetShortPrime()");
        _txtRes = (EditText)findViewById(R.id.editText2);
        GridView main = (GridView)findViewById(R.id.gridView1);
        String[] strs = OperatorsFactory.getAllFunctions();
        main.setAdapter(new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, strs));
        _EngineeringMode = (CheckBox)findViewById(R.id.checkBox1);
        main.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				try{
					IOperation op = OperatorsFactory.getFunction(arg2); 
					String str = op.getOperatorDescription();
					if(!op.isBinary())
						str += "(";
					_txt.append(str);
				}
				catch(Exception ex){
					
				}
			}
			
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void SolveIt(View v){
    	try{
    		//BigDecimal a = new BigDecimal("1");
    		//BigDecimal b = new BigDecimal("3");
    		//BigDecimal c = a.divide(b, 50, RoundingMode.HALF_EVEN);
    		//Toast.makeText(this, c.toString(), Toast.LENGTH_LONG).show();
    		IValue val = OperatorsFactory.Parse(_txt.getText().toString());
    		if(_EngineeringMode.isChecked())
    			_txtRes.setText(val.toEngineeringText());
    		else
    			_txtRes.setText(val.toPlainText());
    	}
    	catch(TerminatedException tex){
    		Toast.makeText(this, tex.getMessage(), Toast.LENGTH_LONG).show();
    	}
    	catch(Exception ex){
        	Toast.makeText(this, String.format("%1$s:%2$s", ex.getClass().getName(), ex.getMessage()), Toast.LENGTH_LONG).show();
    	}
    }
    
}
