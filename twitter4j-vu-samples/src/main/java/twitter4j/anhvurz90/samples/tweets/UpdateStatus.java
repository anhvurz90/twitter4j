/*
 * Copyright (C) 2003-2015 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package twitter4j.anhvurz90.samples.tweets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Example application that uses OAuth method to acquire access to your account.<br/>
 * This application illustrates how to use OAuth method with Twitter4J.<br/>
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 9, 2015  
 * 
 * Key point:
    Twitter twitter = TwitterFactory.getSingleton();
    Status status = twitter.updateStatus(latestStatus);
    System.out.println("Successfully updated the status to [" + status.getText() + "].");
 */
public class UpdateStatus {

  public static void main(String[] args) {
    String newText = "Text 3 from Twitter4j by anhvurz90";
    try {
      Twitter twitter = new TwitterFactory().getInstance();
      try {
        // get request token
        // this will throw IllegalStateException if access token is already available
        RequestToken requestToken = twitter.getOAuthRequestToken();
        System.out.println("Got request token");
        System.out.println("Request token: " + requestToken.getToken());
        System.out.println("Request token secret: " + requestToken.getTokenSecret());
        AccessToken accessToken = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == accessToken) {
          System.out.println("Open the following URL and grant access to your account:");
          System.out.println(requestToken.getAuthorizationURL());
          System.out.println("Enter the PIN(if available) and hit enter after your granted access.[PIN]:");
          String pin = br.readLine();
          try {
            accessToken = pin.length() > 0 ? twitter.getOAuthAccessToken(requestToken, pin) :
                                             twitter.getOAuthAccessToken(requestToken);
          } catch (TwitterException te) {
            if (401 == te.getStatusCode()) {
              System.out.println("Unable to get the access token.");
            } else {
              te.printStackTrace();
            }
          }
        }
        System.out.println("Got access token.");
        System.out.println("Access token: " + accessToken.getToken());
        System.out.println("Access token secret: " + accessToken.getTokenSecret());
      } catch (IllegalStateException e) {
        // access token is already available, or consumer key/secret is not set.
        if (!twitter.getAuthorization().isEnabled()) {
          System.out.println("OAuth consumer key/secret is not set.");
          System.exit(-1);
        }
      }
      Status status = twitter.updateStatus(newText);
      System.out.println("Successfully updated the status to [" + status.getText() + "].");
      System.exit(0);
    } catch (TwitterException te) {
      te.printStackTrace();
      System.out.println("Failed to get timeline: " + te.getMessage());
      System.exit(-1);
    } catch (IOException ioe) {
      ioe.printStackTrace();
      System.out.println("Failed to read the system input.");
      System.exit(-1);
    }
  }
}
