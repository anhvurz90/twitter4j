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
package twitter4j.anhvurz90.samples.search;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 9, 2015  
 */
public class SearchTweets {
  
  public static void main(String[] args) {
    Twitter twitter = TwitterFactory.getSingleton();
    try {
      Query query = new Query("status1");
      QueryResult result;
      do {
        result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        for (Status tweet : tweets) {
          System.out.format("@%s - %s\n", tweet.getUser().getScreenName(), tweet.getText());
        }
      } while ((query = result.nextQuery()) != null);
      System.exit(0);
    } catch (TwitterException te) {
      te.printStackTrace();
      System.out.println("Failed to search tweets: " + te.getMessage());
      System.exit(-1);
    }
  }

}
