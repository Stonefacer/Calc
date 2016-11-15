package com.main.math.operators;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;
import com.main.math.IOperation;
import com.main.math.IValue;
import com.main.math.ValueBase;

public class ModPow extends OperatorBase {

	@Override
	public int getArgsCountRequired() {
		return 3;
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
		BigInteger c = _args.get(2).toBigDecimal().toBigInteger();
		BigInteger d = a.modPow(b, c);
		return new ValueBase(new BigDecimal(d));
	}

	@Override
	public boolean isBinary() {
		return false;
	}

	@Override
	public String getOperatorDescription() {
		return "ModPow";
	}

	@Override
	public String getComments() {
		return "ModPow(num, pow, mod)";
	}

	@Override
	public IOperation createCopy() {
		return new ModPow();
	}

}
