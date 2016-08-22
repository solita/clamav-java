package fi.solita.clamav;

import java.io.InputStream;
import java.util.Random;

/** 
 * Random bytes generated, until in the end it slows down.. this enables testing the  clamd 
 * stream length limit as clamd has enough time to reply, close the socket or whatever.
 */
public class SlowInputStream extends InputStream {

  private static long SLOW_BYTE = 1000;
  private long bytesAvail = 60000;
  private Random r = new Random();

    @Override
    public int read() {
      int nextByte = r.nextInt(256);
      if ((bytesAvail < 12000) && ((bytesAvail % SLOW_BYTE) == 0)) {
        try {
          Thread.sleep(80); // 80 ms sleep
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
      bytesAvail--;
      return (bytesAvail == 0) ? -1 : nextByte;
    }
} 
