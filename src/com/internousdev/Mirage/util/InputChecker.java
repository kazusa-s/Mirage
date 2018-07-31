package com.internousdev.Mirage.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;



public class InputChecker {

	public List<String> doCheck(String propertyName,String value,int minLength,int maxLength,boolean availableAlphabeticCharacters,boolean availableKanji,boolean availableHiragana,boolean availableHalfWidthDigit,boolean availableHalfWidthSymbols,boolean availableKatakana,boolean availableFullWidthSymbols){

		List<String> stringList = new ArrayList<String>();
		List<String> characterTypeList = new ArrayList<String>();

		if(StringUtils.isEmpty(value)){
			stringList.add(propertyName +"を入力してください。");
		}

		if(!(StringUtils.isEmpty(value)) && value.length()<minLength || value.length()>maxLength){
			stringList.add(propertyName+"は"+minLength+"文字以上"+maxLength+"文字以内で入力してください");
		}

		String regularE="";
		String errorE="";

		if(availableAlphabeticCharacters || availableKanji || availableHiragana || availableHalfWidthDigit || availableHalfWidthSymbols ||availableKatakana || availableFullWidthSymbols){
			regularE="[^";
		}
		if(!(availableAlphabeticCharacters) || !(availableKanji) || !(availableHiragana) || !(availableHalfWidthDigit) || !(availableHalfWidthSymbols) || !(availableKatakana) || !(availableFullWidthSymbols)){
			errorE="[^";
		}
		if(availableAlphabeticCharacters){
			regularE +="a-zA-Z";
			characterTypeList.add("半角英字");
		}else{
			errorE +="a-zA-Z";
		}

		if(availableKanji){
			regularE +="一-龯";
			characterTypeList.add("漢字");
		}else{
			errorE +="一-龯";
		}

		if(availableHiragana){
			regularE+="ぁ-ん";
			characterTypeList.add("ひらがな");
		}else{
			errorE+="ぁ-ん";
		}

		if(availableHalfWidthDigit){
			regularE +="0-9";
			characterTypeList.add("半角数字");
		}else{
			errorE +="0-9";
		}

		if(availableHalfWidthSymbols){
			regularE +="@ .,;:!#$%&'*+-/=?^_`{|}~";
			characterTypeList.add("半角記号");
		}else{
			errorE +="@ .,;:!#$%&'*+-/=?^_`{|}~";
		}

		if(availableKatakana){
			regularE +="ァ-ヺ";
			characterTypeList.add("");
		}else{
			errorE +="ァ-ヺ";
		}

		if(availableFullWidthSymbols){
			regularE +="＠　．，；：！＃＄％＆’＊＋―／＝？＾＿｀｛｜｝～";
			characterTypeList.add("全角記号");
		}else{
			errorE +="＠　．，；：！＃＄％＆’＊＋―／＝？＾＿｀｛｜｝～";
		}

		if(!StringUtils.isEmpty(regularE)){
			regularE +="]+";
		}
		if(!StringUtils.isEmpty(errorE)){
			errorE +="]+";
		}

		String characterType ="";
		for(int i =0;i<characterTypeList.size();i++){
			if(i==0){
				characterType += characterTypeList.get(i).toString();
			}else{
				characterType += "、"+characterTypeList.get(i).toString();
			}
		}
		if(errorE.equals("")){
			if(value.matches(regularE)){
				stringList.add(propertyName + "は" +characterType + "で入力してください。");
			}
		}else{
			if(value.matches(regularE) || (!value.matches(errorE) && !value.equals(""))){
				stringList.add(propertyName + "は" +characterType + "で入力してください。");
			}
		}
		return stringList;
	}

	public List<String> doPasswordCheck(String password,String reConfirmationPassword){
			List<String> stringList = new ArrayList<String>();
		if(!(password.equals(reConfirmationPassword))){
			stringList.add("入力されたパスワードと異なります。");
		}
		return stringList;
	}
}
