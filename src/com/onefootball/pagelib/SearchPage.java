package com.onefootball.pagelib;

import java.util.List;

import com.onefootball.baselib.SolventSelenium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;

public class SearchPage extends SolventSelenium{
	
	//LOCATORS
	private String searchBoxID = "de.motain.iliga:id/search_src_text";
	
	public void enterTextToSearch(String searchText) {
		getElement(searchBoxID, LocatorType.ID).sendKeys(searchText);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<MobileElement> getSearchResults() {
		
		return getChildElementListWithClassname("de.motain.iliga:id/container", "android.widget.LinearLayout", 0); 
}
}