package com.main.math;

import java.lang.*;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;

public interface IOperation extends Comparable<IOperation>, IValue {
	
	void addArg(IValue... val);
	int getArgsCount();
	int getArgsCountRequired();
	int getPriority();
	IValue solve() throws InvalidArgumentException, TerminatedException;
	boolean isBinary();
	String getOperatorDescription();
	String getComments();
	IOperation createCopy();
	boolean isEnoughArgs();
	
}
