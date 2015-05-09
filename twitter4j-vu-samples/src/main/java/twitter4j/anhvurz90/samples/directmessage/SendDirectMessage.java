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
package twitter4j.anhvurz90.samples.directmessage;

import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 9, 2015  
 */
public class SendDirectMessage {

  public static void main(String[] args) {
    Twitter twitter = TwitterFactory.getSingleton();
    try {
      DirectMessage message = twitter.sendDirectMessage("tungtns", 
                      "Sorry i anh la neu 'CHU' nhan dc" +
                      "Luc dau a send cho thang Giang nhung ko dc vi no ko follow anh :P"
      );
      System.out.println("Direct message successfully send to " + message.getRecipientScreenName());
      System.exit(0);
    } catch (TwitterException te) {
      te.printStackTrace();
      System.out.println("Failed to send a direct message: " + te.getMessage());
      System.exit(-1);
    }
  }
}
