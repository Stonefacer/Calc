package com.main.math.operators;

import java.math.RoundingMode;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;
import com.main.math.IOperation;
import com.main.math.IValue;
import com.main.math.ValueBase;

public class Sub extends OperatorBase {

	@Override
	public int getArgsCountRequired() {
		return 2;
	}

	@Override
	public int getPriority() {
		return 1000;
	}

	@Override
	public IValue solve() throws InvalidArgumentException, TerminatedException {
		return new ValueBase(_args.get(0).toBigDecimal().divide(_args.get(1).toBigDecimal(), ValueBase.Scale, RoundingMode.HALF_EVEN));
	}

	@Override
	public boolean isBinary() {
		return true;
	}

	@Override
	public String getOperatorDescription() {
		return "/";
	}

	@Override
	public String getComments() {
		return "/";
	}

	@Override
	public IOperation createCopy() {
		return new Sub();
	}

}
