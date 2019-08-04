package com.onefootball.pagelib;

import com.onefootball.baselib.SolventSelenium;

public class HomePage extends SolventSelenium {
	
	public void navigateToSearchPage() {
		getElement("Search for my team…", LocatorType.DESCRIPTION).click();
	}

}
