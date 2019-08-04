package com.onefootball.pagelib;

import com.onefootball.baselib.SolventSelenium;

public class UserOnboard extends SolventSelenium{
	
	public void pickFavoriteTeam(String teamName) {
		
		getElement(teamName, LocatorType.TEXT).click();
		
	}
	
	public void getStarted() {
		
		getElement("Get started", LocatorType.TEXT).click();
		
	}

}
