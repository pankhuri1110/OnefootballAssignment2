package com.onefootball.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;

import com.onefootball.baselib.SolventSelenium;
import com.onefootball.pagelib.HomePage;
import com.onefootball.pagelib.SearchPage;
import com.onefootball.pagelib.UserOnboard;

import io.appium.java_client.MobileElement;

@Listeners(com.onefootball.baselib.TestListener.class)
public class Search extends SolventSelenium{

   @BeforeClass
   @Parameters({ "filepath" })
   public void setUp(String filepath) throws IOException {
	  loadProperties(filepath);
	  launchApp();
   }

   @Test
   public void testSearch() throws MalformedURLException, InterruptedException{

      System.out.println("Select Favorite team of the User and Get Started");
      
      String searchString = "Arg";
      boolean flag = true;

      UserOnboard newuser = new UserOnboard();
      newuser.pickFavoriteTeam("Barcelona");
      newuser.getStarted();

      System.out.println("Navigate to Search Page by clicking Search icon");
      HomePage home = new HomePage();
      home.navigateToSearchPage();

      System.out.println("Enter Text To search");
      SearchPage search = new SearchPage();
      search.enterTextToSearch(searchString);

      System.out.println("Getting Search Results");
      List<MobileElement> results = search.getSearchResults();
      
      System.out.println("Validating Search Results");
      for (Iterator iterator = results.iterator(); iterator.hasNext();) {
    	  MobileElement mobileElement = (MobileElement) iterator.next();
	      String searchResult = mobileElement.getText().toLowerCase();
	      if(!mobileElement.getText().toLowerCase().contains(searchString.toLowerCase())) {
	    	  System.out.println("Search result : " + searchResult + " does not conatins search string : " + searchString);
	    	  flag = false;
	      }
	  }
      
      Assert.assertTrue(flag, "Search test failed");
   }
 }