package com.internousdev.Mirage.util;

import java.util.ArrayList;
import java.util.List;

public class CommonUtility {


	// 乱数取得

	public String getRandomValue(){
		String value = "";
		double d;

	    // Math.random() 0.0以上1.0未満までのランダムな浮動小数点を得ることができる。
		for(int i=0;i<16;i++){
		d= Math.random()*10;
		value = value+(int)d;
		}
		return value;
	}

	// 文字列を区切り、文字にて分解

	public String[] parseArrayList(String s){
		//区切り文字としてカンマ+半角スペースを指定、最後の空白は配列に含めない
		return s.split(", ",0);
	}

	//Listを指定したサイズごとに分割

	public <E>List<List<E>> devideList(List<E> list,int size){
		if(list == null || list.isEmpty() || size<=0){
			return null;
		}
         /*
          * 条件演算子
          * ex.)  x = condition ? a : b;
          * condition が true = a ,false = b が　xに代入される
          */

		int block = list.size()/size + (list.size() % size> 0 ? 1:0);
		List<List<E>> devidedList = new ArrayList<List<E>>(block);
		for(int i=0;i<block;i++){
			int start = i*size;
			int end = Math.min(start + size,list.size());
			devidedList.add(new ArrayList<E>(list.subList(start, end)));

		}
		return devidedList;
	}

}
