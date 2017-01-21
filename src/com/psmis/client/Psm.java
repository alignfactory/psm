package com.psmis.client;

import com.psmis.client.main.Login;
import com.google.gwt.core.client.EntryPoint;

public class Psm implements EntryPoint {

	public void onModuleLoad() {
		Login login = new Login();
		login.open(); //로그인 오픈 
	}
}
