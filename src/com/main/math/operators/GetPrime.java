package com.main.math.operators;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;
import com.main.math.IOperation;
import com.main.math.IValue;
import com.main.math.ValueBase;

public class GetPrime extends OperatorBase {

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
		Random rnd = new Random();
		return new ValueBase( new BigDecimal(BigInteger.probablePrime(_args.get(0).toInt(), rnd)));
	}

	@Override
	public boolean isBinary() {
		return false;
	}

	@Override
	public String getOperatorDescription() {
		return "GetPrime";
	}

	@Override
	public String getComments() {
		return "GetPrime(bits)";
	}

	@Override
	public IOperation createCopy() {
		return new GetPrime();
	}

}
