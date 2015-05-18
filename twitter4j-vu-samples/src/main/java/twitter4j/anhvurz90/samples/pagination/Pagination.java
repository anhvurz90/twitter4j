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
package twitter4j.anhvurz90.samples.pagination;

import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 18, 2015  
 */
public class Pagination {

  public static void main(String[] args) throws TwitterException {
    //The factory instance is re-usable and thread safe
    Twitter twitter = TwitterFactory.getSingleton();
    // requesting page 2, number of elements per page is 40
    Paging paging = new Paging(2, 40);
    List<Status> statuses = twitter.getHomeTimeline(paging);
    for (Status status : statuses) {
      System.out.format("%s: %s\n", status.getUser().getScreenName(), status.getText());
    }
    System.out.println("----------------------------------------------------------");
    // requesting page 3, since_id is 1000
    statuses = twitter.getHomeTimeline(new Paging(3).sinceId(1000l));
    for (Status status : statuses) {
      System.out.format("%s: %s\n", status.getUser().getScreenName(), status.getText());
    }
    
  }
}
