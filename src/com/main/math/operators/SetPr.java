package com.main.math.operators;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;
import com.main.math.IOperation;
import com.main.math.IValue;
import com.main.math.ValueBase;

public class SetPr extends OperatorBase {

	@Override
	public int getArgsCountRequired() {
		return 1;
	}

	@Override
	public int getPriority() {
		return 3000;
	}

	@Override
	public IValue solve() throws InvalidArgumentException, TerminatedException {
		int nums = _args.get(0).toInt();
		if(nums<10||nums>100000)
			return new ValueBase(0);
		ValueBase.Scale = nums;
		return new ValueBase(1);
	}

	@Override
	public boolean isBinary() {
		return false;
	}

	@Override
	public String getOperatorDescription() {
		return "setpr";
	}

	@Override
	public String getComments() {
		return "SetPr(precision)";
	}

	@Override
	public IOperation createCopy() {
		return new SetPr();
	}

}
