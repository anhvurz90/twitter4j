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
package twitter4j.anhvurz90.samples.async;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterMethod;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 13, 2015  
 */
public class AsyncUpdate {

  private static final Object LOCK  = new Object();
  
  public static void main(String[] args) throws InterruptedException {
    String msg = "Text 4 from Twitter4j by anhvurz90";
    AsyncTwitter twitter = AsyncTwitterFactory.getSingleton();
    twitter.addListener(new TwitterAdapter() {
      @Override
      public void updatedStatus(Status status) {
        System.out.println("Successfully updated the status to " + status.getText());
        synchronized (LOCK) {
          LOCK.notify();
        }
      }
      
      @Override
      public void onException(TwitterException e, TwitterMethod method) {
        if (method == TwitterMethod.UPDATE_STATUS) {
          e.printStackTrace();
          synchronized(LOCK) {
            LOCK.notify();
          }
        } else {
          synchronized (LOCK) {
            LOCK.notify();
          }
          throw new AssertionError("Should not happen!");
        }
      }
    });
    
    twitter.updateStatus(msg);
    synchronized (LOCK) {
      LOCK.wait();
    }
  }
}
