package com.main.math.operators;

import java.math.BigDecimal;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;
import com.main.math.IOperation;
import com.main.math.IValue;
import com.main.math.ValueBase;

public class Pow extends OperatorBase {

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
		BigDecimal a = _args.get(0).toBigDecimal();
		int b = _args.get(1).toInt();
		BigDecimal c = a.pow(b);
		return new ValueBase(c);
	}

	@Override
	public boolean isBinary() {
		return false;
	}

	@Override
	public String getOperatorDescription() {
		return "Pow";
	}

	@Override
	public String getComments() {
		return "Pow(num, pow)";
	}

	@Override
	public IOperation createCopy() {
		return new Pow();
	}

}
