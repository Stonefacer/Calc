package com.main.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.main.exceptions.InvalidArgumentException;
import com.main.exceptions.TerminatedException;


public interface IValue {
	
	double toDouble() throws InvalidArgumentException, TerminatedException;
	int toInt() throws InvalidArgumentException, TerminatedException;
	long toLong() throws InvalidArgumentException, TerminatedException;
	BigDecimal toBigDecimal() throws InvalidArgumentException, TerminatedException;
	BigInteger toBigInt() throws InvalidArgumentException, TerminatedException;
	String toPlainText() throws InvalidArgumentException, TerminatedException;
	String toEngineeringText() throws InvalidArgumentException, TerminatedException;
	
}
