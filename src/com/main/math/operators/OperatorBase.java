package com.main.math.operators;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;
import com.main.math.IOperation;
import com.main.math.IValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

abstract class OperatorBase implements IOperation {

	protected LinkedList<IValue> _args = new LinkedList<IValue>();
	
	@Override
	public void addArg(IValue... val) {
		for(IValue v:val){
			_args.add(v);
		}
	}
	
	@Override
	public int getArgsCount(){
		return _args.size();
	}
	
	@Override
	public int compareTo(IOperation arg0) {
		return getPriority() - arg0.getPriority();
	}
	
	@Override
	public double toDouble() throws InvalidArgumentException, TerminatedException {
		return solve().toDouble();
	}

	@Override
	public int toInt() throws InvalidArgumentException, TerminatedException {
		return solve().toInt();
	}

	@Override
	public long toLong() throws InvalidArgumentException, TerminatedException {
		return solve().toLong();
	}
	
	@Override
	public BigDecimal toBigDecimal() throws InvalidArgumentException, TerminatedException{
		return solve().toBigDecimal();
	}
	
	@Override
	public BigInteger toBigInt() throws InvalidArgumentException, TerminatedException{
		return solve().toBigInt();
	}
	
	@Override
	public String toPlainText() throws InvalidArgumentException, TerminatedException{
		return solve().toPlainText();
	}
	
	@Override
	public String toEngineeringText() throws InvalidArgumentException, TerminatedException{
		return solve().toEngineeringText();
	}
	
	@Override
	public boolean isEnoughArgs(){
		return getArgsCount()==getArgsCountRequired();
	}

}
