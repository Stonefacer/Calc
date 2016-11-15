package com.main.math.operators;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;
import com.main.math.IOperation;
import com.main.math.IValue;
import com.main.math.ValueBase;

public class Mod extends OperatorBase {

	@Override
	public int getArgsCountRequired() {
		return 2;
	}

	@Override
	public int getPriority() {
		return 2000;
	}

	@Override
	public IValue solve() throws InvalidArgumentException, TerminatedException {
		if(!isEnoughArgs())
			throw new InvalidArgumentException("Incorrect number of arguments("+getArgsCount()+"/"+getArgsCountRequired()+") for operator \""+getOperatorDescription()+"\"");
		BigInteger a = _args.get(0).toBigDecimal().toBigInteger();
		BigInteger b = _args.get(1).toBigDecimal().toBigInteger();
		BigInteger c = a.mod(b);
		return new ValueBase(new BigDecimal(c));
	}

	@Override
	public boolean isBinary() {
		return false;
	}

	@Override
	public String getOperatorDescription() {
		return "mod";
	}

	@Override
	public String getComments() {
		return "Mod(number, module)";
	}

	@Override
	public IOperation createCopy() {
		return new Mod();
	}
}
