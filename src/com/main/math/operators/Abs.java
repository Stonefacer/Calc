package com.main.math.operators;

import java.math.BigDecimal;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;
import com.main.math.IOperation;
import com.main.math.IValue;
import com.main.math.ValueBase;

public class Abs extends OperatorBase {

	@Override
	public int getArgsCountRequired() {
		return 1;
	}

	@Override
	public int getPriority() {
		return 2000;
	}

	@Override
	public IValue solve() throws InvalidArgumentException, TerminatedException {
		BigDecimal a = _args.get(0).toBigDecimal();
		BigDecimal c = a.abs();
		return new ValueBase(c);
	}

	@Override
	public boolean isBinary() {
		return false;
	}

	@Override
	public String getOperatorDescription() {
		return "Abs";
	}

	@Override
	public String getComments() {
		return "Abs(num)";
	}

	@Override
	public IOperation createCopy() {
		return new Abs();
	}

}
