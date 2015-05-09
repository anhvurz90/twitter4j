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
package twitter4j.anhvurz90.samples.timeline;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 9, 2015  
 */
public class GetHomeTimeLine {

  public static void main(String[] args) {
    try {
      Twitter twitter = TwitterFactory.getSingleton();
      User user = twitter.verifyCredentials();
      List<Status> statuses = twitter.getHomeTimeline();
      System.out.format("Showing @%s's home timeline.\n", user.getScreenName());
      for (Status status : statuses) {
        System.out.format("@%s - %s\n", status.getUser().getScreenName(), status.getText());
      }
    } catch (TwitterException te) {
      te.printStackTrace();
      System.out.println("Failed to get timeline: " + te.getMessage());
      System.exit(-1);
    }
  }
}
