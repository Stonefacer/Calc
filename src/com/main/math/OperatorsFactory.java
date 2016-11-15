package com.main.math;

import java.math.BigInteger;
import java.util.*;

import com.main.exceptions.IllegalOperatorException;
import com.main.exceptions.InvalidArgumentException;
import com.main.math.*;
import com.main.math.operators.*;

public class OperatorsFactory {
	
	private static IOperation[] _Operations;
	
	static{
		_Operations = new IOperation[]{
				new Plus(), new Minus(), new Mul(), new Sub(),
				new Mod(), new SetPr(), new ModPow(), new gcd(),
				new lcm(), new Abs(), new Pow(), new GetPrime(),
				new ModInverse(), new GetShortPrime()
		};
		Arrays.sort(_Operations, new Comparator<IOperation>(){
			
			@Override
			public int compare(IOperation lhs, IOperation rhs) {
				int l = lhs.compareTo(rhs);
				if(l==0)
					return lhs.getOperatorDescription().compareToIgnoreCase(rhs.getOperatorDescription());
				return l;
			}
			
		});
	}
	
	public static String[] getAllFunctions(){
		String[] strs = new String[_Operations.length];
		int i = 0;
		for(IOperation op:_Operations){
			String str = op.getComments();
			if(str==null)
				str = "";
			strs[i++] = str;
		}
		return strs;
	}
	
	public static IOperation getFunction(int id){
		return _Operations[id];
	}
	
	private static String readOperator(String str, int start, boolean binary){
		String res = "";
		int id = start;
		char ch = str.charAt(id);
		if(binary){
			res += ch;
			return res;
		}
		while(id<str.length()&&(!Character.isDigit(ch))&&ch!='('){
			res+=ch;
			id++;
			ch = str.charAt(id);
		}
		return res;
	}
	
	private static IOperation getByName(String str) throws IllegalOperatorException{
		for(IOperation op:_Operations){
			if(op.getOperatorDescription().equalsIgnoreCase(str))
				return op.createCopy();
		}
		throw new IllegalOperatorException("Illegal operator \""+str+"\"");
	}

	private static LinkedList<IValue> getFunctionArguments(String str, int start, int[] IdRes) throws IllegalOperatorException, InvalidArgumentException{
		LinkedList<IValue> res = new LinkedList<IValue>();
		int id = start + 2;
		String arg = "";
		char ch;
		while(id<str.length()){
			ch = str.charAt(id);
			if(ch==')')
				break;
			else if(ch==','){
				res.add(Parse(arg));
				arg = "";
			}
			else if(ch==' '){
				
			}
			else{
				arg += ch;
			}
			id++;
			ch = str.charAt(id);
		}
		if(!arg.equalsIgnoreCase("")){
			res.add(Parse(arg));
		}
		IdRes[0] = id;
		return res;
	}
	
	private static String readMaxPrior(String str, int start) throws IllegalOperatorException{
		int lvl = 0;
		char ch;
		StringBuilder sb = new StringBuilder();
		while(start<str.length()){
			ch = str.charAt(start);
			if(ch=='('){
				if(lvl!=0)
					sb.append(ch);
				lvl++;
			}
			else if(ch==')'){
				lvl--;
				if(lvl==0)
					return sb.toString();
				sb.append(ch);
			}
			else
				sb.append(ch);
			start++;
		}
		throw new IllegalOperatorException("')' expected at the end of \""+sb.toString()+"\"");
	}
	
	public static IValue Parse(String str) throws IllegalOperatorException, InvalidArgumentException{
		int id = 0;
		String arg = "";
		IOperation prev = null;
		IOperation first = null;
		IValue firstValue = null;
		while(id<str.length()){
			char ch = str.charAt(id);
			if(Character.isDigit(ch)||ch=='.'){
				arg+=ch;
			}
			else if(ch==' '){
				
			}
			else if(ch=='('){
				String str2 = readMaxPrior(str, id);
				firstValue = Parse(str2);
				id += str2.length()+1;
				if(prev!=null){
					prev.addArg(firstValue);
					firstValue = null;
				}
			}
			else if(Character.isLetter(ch)){
				String op = readOperator(str, id, false);
				IOperation v = getByName(op);
				id += v.getOperatorDescription().length()-1;
				if(prev!=null)
					prev.addArg(v);
				else
					first = v;
				int [] idRes = new int[]{0};
				LinkedList<IValue> ars = getFunctionArguments(str, id, idRes);
				if(ars.size()>0){
					IValue[] val = new IValue[ars.size()];			
					v.addArg(ars.toArray(val));
				}
				id = idRes[0];
				arg = "";
			}
			else{
				String op = readOperator(str, id, true);
				IOperation v = getByName(op);
				id += v.getOperatorDescription().length()-1;
				if(prev!=null){
					if(prev.isBinary()){
						if(!prev.isEnoughArgs())
							prev.addArg(new ValueBase(arg));
					}
					v.addArg(prev);
				}
				else if(first!=null){
					v.addArg(first);
					first = null;
				}
				else{
					v.addArg(new ValueBase(arg));
				}
				arg = "";
				prev = v;
			}
			id++;
		}
		if(prev==null){
			if(first==null)
				return new ValueBase(arg);
			else
				return first;
		}
		else{
			if(prev.isBinary()&&!arg.equals("")){
				prev.addArg(new ValueBase(arg));
			}
			return prev;
		}
	}
}
