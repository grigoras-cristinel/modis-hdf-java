package hdfextractor;

import javax.vecmath.Point2d;

import com.eclipsesource.json.JsonObject;

/**
 * Structura pt statia meteo
 * 
 * @author grig
 *
 */
public class StatieMeteoPoint {

   String name;
   Point2d point = new Point2d();

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Point2d getPoint() {
      return point;
   }

   public void setPoint(Point2d point) {
      this.point = point;
   }

   public StatieMeteoPoint(String name, Point2d point) {
      super();
      this.name = name;
      this.point = point;
   }

   public StatieMeteoPoint(String name, double pointx, double pointy) {
      super();
      this.name = name;
      this.point = new Point2d(pointx, pointy);
   }

   public StatieMeteoPoint() {
      super();
   }

   public static StatieMeteoPoint fromJson(String json) {
      JsonObject o = JsonObject.readFrom(json);
      o.get("n");
      o.get("x");
      o.get("y");
      StatieMeteoPoint rowp = new StatieMeteoPoint(o.get("n").asString(),
            new Point2d(o.get("x").asDouble(), o.get("y").asDouble()));
      return rowp;
   }

   public String toJson() {
      JsonObject o = new JsonObject();
      o.add("n", getName());
      o.add("x", getPoint().x);
      o.add("y", getPoint().y);
      return o.toString();
   }
}
