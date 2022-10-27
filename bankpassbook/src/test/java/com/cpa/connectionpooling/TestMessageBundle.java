
package com.cpa.connectionpooling;

import java.util.ResourceBundle;

public class TestMessageBundle {

	private static TestMessageBundle mb = null;
	private ResourceBundle rb = null;
	private static final String RESOURCE_BUNDLE = "ErrorMessage";

	private TestMessageBundle() {
		loadBundle();
	}

	private void loadBundle() {
		try {
//			System.out.println("Inside Load bundle");
			// rb = ResourceBundle.getBundle(RESOURCE_BUNDLE, new Locale("en_IN"),
			// this.getClass().getClassLoader());
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public String getMessage(String code) {
		return rb.getString(code);
	}

	public static TestMessageBundle getBundle() {
		if (null == mb) {
			mb = new TestMessageBundle();
		}
		// System.out.println(mb);
		return mb;
	}
}
