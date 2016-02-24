package ro.grig.face.util;

import javax.vecmath.Point2d;

public class GoogleMapsAPIProjection {

   private double PixelTileSize = 256d;
   private double DegreesToRadiansRatio = 180d / Math.PI;
   private double RadiansToDegreesRatio = Math.PI / 180d;
   private Point2d PixelGlobeCenter;
   private double XPixelsToDegreesRatio;
   private double YPixelsToRadiansRatio;

   public GoogleMapsAPIProjection(double zoomLevel) {
      double pixelGlobeSize = this.PixelTileSize * Math.pow(2d, zoomLevel);
      this.XPixelsToDegreesRatio = pixelGlobeSize / 360d;
      this.YPixelsToRadiansRatio = pixelGlobeSize / (2d * Math.PI);
      double halfPixelGlobeSize = pixelGlobeSize / 2d;
      this.PixelGlobeCenter = new Point2d(halfPixelGlobeSize, halfPixelGlobeSize);
   }

   public Point2d FromCoordinatesToPixel(Point2d coordinates) {
      double x = Math.round(this.PixelGlobeCenter.x + (coordinates.x * this.XPixelsToDegreesRatio));
      double f = Math.min(Math.max(Math.sin(coordinates.y * RadiansToDegreesRatio), -0.9999d), 0.9999d);
      double y = Math
            .round(this.PixelGlobeCenter.y + .5d * Math.log((1d + f) / (1d - f)) * -this.YPixelsToRadiansRatio);
      return new Point2d(x, y);
   }

   public Point2d FromPixelToCoordinates(Point2d pixel) {
      double longitude = (pixel.x - this.PixelGlobeCenter.x) / this.XPixelsToDegreesRatio;
      double latitude = (2 * Math.atan(Math.exp((pixel.y - this.PixelGlobeCenter.y) / -this.YPixelsToRadiansRatio))
            - Math.PI / 2) * DegreesToRadiansRatio;
      return new Point2d(latitude, longitude);
   }
}
