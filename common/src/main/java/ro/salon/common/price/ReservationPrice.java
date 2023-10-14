package ro.salon.common.price;

public class ReservationPrice {

  public float hairRemovalPrice(String hairRemove) {
    float total = 0;
    float legs = 20;
    float hands = 18;
    float pubic = 25;
    float all = 60;

    if (hairRemove.equals("NONE")) {
      total = 0;
    } else if (hairRemove.equals("LEGS")) {
      total += legs;
    } else if (hairRemove.equals("HANDS")) {
      total += hands;
    } else if (hairRemove.equals("PUBIC")) {
      total += pubic;
    } else if (hairRemove.equals("LEGS AND HANDS")) {
      total = legs + hands;
    } else if (hairRemove.equals("LEGS AND PUBIC")) {
      total = pubic + legs;
    } else if (hairRemove.equals("HANDS AND PUBIC")) {
      total = hands + pubic;
    } else if (hairRemove.equals("ALL")) {
      total = all;
    }

    return total;
  }

  public float eyebrowsPrice(String eyebrows) {
    float total = 0;
    float paint = 10;
    float shaping = 12;
    float trimmed = 8;
    float all = 25;

    if (eyebrows.equals("NONE")) {
      total = 0;
    } else if (eyebrows.equals("PAINT")) {
      total += paint;
    } else if (eyebrows.equals("SHAPING")) {
      total += shaping;
    } else if (eyebrows.equals("TRIMMED")) {
      total += trimmed;
    } else if (eyebrows.equals("PAINT AND SHAPING")) {
      total = paint + shaping;
    } else if (eyebrows.equals("PAINT AND TRIMMED")) {
      total = paint + trimmed;
    } else if (eyebrows.equals("SHAPING AND TRIMMED")) {
      total = shaping + trimmed;
    } else if (eyebrows.equals("ALL")) {
      total = all;
    }
    return total;
  }

  public float manicurePrice(String manicure) {
    float total = 0;
    float semipermanent = 18;
    float permanent = 12;
    float simpleManicure = 10;

    if (manicure.equals("NONE")) {
      total = 0;
    } else if (manicure.equals("PERMANENT")) {
      total += permanent;
    } else if (manicure.equals("SEMIPERMANENT")) {
      total += semipermanent;
    } else if (manicure.equals("SIMPLE MANICURE")) {
      total += simpleManicure;
    }
    return total;
  }

  public float pedicurePrice(String pedicure) {
    float total = 0;
    float semipermanent = 18;
    float permanent = 12;
    float simpleManicure = 10;

    if (pedicure.equals("NONE")) {
      total = 0;
    } else if (pedicure.equals("PERMANENT")) {
      total += permanent;
    } else if (pedicure.equals("SEMIPERMANENT")) {
      total += semipermanent;
    } else if (pedicure.equals("SIMPLE PEDICURE")) {
      total += simpleManicure;
    }
    return total;
  }

  public float getPrice(float hairRemoval, float eyebrows, float manicure, float pedicure) {
    float total = hairRemoval + eyebrows + manicure + pedicure;
    return total;
  }

}
