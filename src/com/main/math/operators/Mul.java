package com.main.math.operators;

import java.math.BigDecimal;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;
import com.main.math.IOperation;
import com.main.math.IValue;
import com.main.math.ValueBase;

public class Mul extends OperatorBase {

	@Override
	public int getArgsCountRequired() {
		return 2;
	}

	@Override
	public int getPriority() {
		return 150;
	}

	@Override
	public IValue solve() throws InvalidArgumentException, TerminatedException {
		if(!isEnoughArgs())
			throw new InvalidArgumentException("Incorrect number of arguments("+getArgsCount()+"/"+getArgsCountRequired()+") for operator \""+getOperatorDescription()+"\"");
		BigDecimal a = _args.get(0).toBigDecimal();
		BigDecimal b = _args.get(1).toBigDecimal();
		BigDecimal c = a.multiply(b);
		return new ValueBase(c);
	}

	@Override
	public boolean isBinary() {
		return true;
	}

	@Override
	public String getOperatorDescription() {
		return "*";
	}

	@Override
	public String getComments() {
		return "*";
	}

	@Override
	public IOperation createCopy() {
		return new Mul();
	}

}
