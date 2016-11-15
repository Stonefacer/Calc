package com.main.math;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ValueBase implements IValue {

	public static int Scale = 200;
	
	private BigDecimal _Val;
	
	public ValueBase(){
		_Val = BigDecimal.ZERO;
	}
	
	public ValueBase(double val){
		_Val = BigDecimal.valueOf(val);
	}
	
	public ValueBase(int val){
		_Val = BigDecimal.valueOf(val);
	}
	
	public ValueBase(String str){
		_Val = new BigDecimal(str);
	}
	
	public ValueBase(BigDecimal bd){
		_Val = bd;
	}
	
	@Override
	public double toDouble() {
		return _Val.doubleValue();
	}

	@Override
	public int toInt() {
		return _Val.intValue();
	}

	@Override
	public long toLong() {
		return _Val.longValue();
	}
	
	@Override
	public BigDecimal toBigDecimal(){
		return _Val;
	}
	
	@Override
	public BigInteger toBigInt(){
		return _Val.toBigInteger();
	}
	
	@Override
	public String toPlainText(){
		return _Val.toPlainString();
	}
	
	@Override
	public String toEngineeringText(){
		return _Val.toEngineeringString();
	}

}
