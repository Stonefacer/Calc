package com.main.math.operators;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import android.widget.Toast;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;
import com.main.math.IOperation;
import com.main.math.IValue;
import com.main.math.ValueBase;

public class GetShortPrime extends OperatorBase {

	@Override
	public int getArgsCountRequired() {
		return 3;
	}

	@Override
	public int getPriority() {
		return 2000;
	}
	
	@Override
	public IValue solve() throws TerminatedException, InvalidArgumentException {
		int pow = 0;
		int max = 0;
		int certainty = 20;
		SecureRandom rnd = new SecureRandom();
		if(_args.size()!=3){
			pow = rnd.nextInt(512)+768;
			max = pow*4;
		}
		else{
			pow = _args.get(0).toInt();
			max = _args.get(1).toInt();
			certainty = _args.get(2).toInt();
		}
		BigInteger num = BigInteger.valueOf(2);
		num = num.pow(pow);
		for(int i=1;i<max;i++){
			int ri = rnd.nextInt(max);
			int offset = ri * 2 + 1;
			if(num.add(BigInteger.valueOf(offset)).isProbablePrime(certainty)){
				String str = String.format("%1$s; %2$s", pow, ri);
				throw new TerminatedException(str);
				//long ans = Long.parseLong(str);
				//return new ValueBase(1);
			}
		}
		return new ValueBase(0);
	}

	@Override
	public boolean isBinary() {
		return false;
	}

	@Override
	public String getOperatorDescription() {
		return "GetShortPrime";
	}

	@Override
	public String getComments() {
		return "GetShortPrime(pow, offset, certainty)";
	}

	@Override
	public IOperation createCopy() {
		return new GetShortPrime();
	}

}
