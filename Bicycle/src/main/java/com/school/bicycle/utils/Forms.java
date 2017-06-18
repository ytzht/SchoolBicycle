package com.school.bicycle.utils;

import android.util.Log;
import android.widget.EditText;

import com.school.bicycle.global.BuildConfig;

import org.nutz.lang.Strings;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public abstract class Forms {
	public static final String TAG = "Forms";
	public static final String NUMBER = "^\\d+$";
//	public static final String EMAIL = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$";//原
	public static final String EMAIL = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";//老顾
//	public static final String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";//网上
	public static final String CHINESE = "^[\u4e00-\u9fa5]+$";
//	public static final String PHONENUM = "^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,2,3,5-9]))\\d{8}$";//原
	public static final String PHONENUM = "^1[3|4|5|7|8][0-9]\\d{8}$";//老顾
	public static final String ACCOUNT = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,}$";
	public static final String SELFNUM = "/^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$/";//身份证号验证

	public static Checkd check(Object contaner) {
		if (contaner == null) {
			return null;
		}

		Class<?> clazz = contaner.getClass();
		if (BuildConfig.DEBUG) {
			Log.d(TAG, clazz.getName() + "开始检查------");
		}
		Field[] fields = clazz.getDeclaredFields();
		Checkd checkd = null;
		for (Field field : fields) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			Object oView = null;
			try {
				oView = field.get(contaner);
			} catch (Exception e) {
			}
			if (!(oView instanceof EditText)) {
				continue;
			}
			Valid cat = field.getAnnotation(Valid.class);
			if (cat == null) {
				continue;
			}

			EditText view = (EditText) oView;
			String value = view.getEditableText().toString();
			if (BuildConfig.DEBUG) {
				Log.d(TAG, field.getName() + ":" + value);
			}
			checkd = checkValue(view, value, cat);
			if (checkd != null) {
				break;
			}
		}

		if (checkd == null) {
			checkd = new Checkd();
		}

		if (BuildConfig.DEBUG) {
			Log.d(TAG, clazz.getName() + "结束检查------");
		}
		return checkd;
	}

	private static Checkd checkValue(EditText view, String value, Valid cat) {
		if (cat.required()) {
			if (Strings.isBlank(value)) {
				return new Checkd();
			}
		}
		return null;
	}

	public static boolean valid(String value, String reg) {
		if (Strings.isBlank(value)) {
			return false;
		}
		Pattern p = Pattern.compile(reg);
		Matcher matcher = p.matcher(value);
		return matcher.matches();
	}
	
	public static boolean disValid(String value, String reg) {
		return !valid(value, reg);
	}
}
