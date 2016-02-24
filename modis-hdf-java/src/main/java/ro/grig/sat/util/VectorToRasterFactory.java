package ro.grig.sat.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.data.Parameter;
import org.geotools.process.feature.AbstractFeatureCollectionProcessFactory;
import org.geotools.text.Text;
import org.opengis.util.InternationalString;

import com.vividsolutions.jts.geom.Envelope;

public class VectorToRasterFactory extends AbstractFeatureCollectionProcessFactory {
   private static final String VERSION = "1.0.0";
   /**
    * Output grid dimensions expressed (in cells).
    */
   static final Parameter<Integer> RASTER_WIDTH = new Parameter<Integer>("rasterWidth", Integer.class,
         Text.text("Width"), Text.text("Number of cells in a raster row"), true, // this
                                                                                 // parameter
                                                                                 // is
                                                                                 // mandatory
         1, 1, null, null);
   static final Parameter<Integer> RASTER_HEIGHT = new Parameter<Integer>("rasterHeight", Integer.class,
         Text.text("Height"), Text.text("Number of cells in a raster column"), true, // this
                                                                                     // parameter
                                                                                     // is
                                                                                     // mandatory
         1, 1, null, null);
   /**
    * Title for the output grid. Optional - if not provided the grid's title
    * will be derived from the feature type.
    */
   static final Parameter<String> TITLE = new Parameter<String>("title", String.class, Text.text("Title"),
         Text.text("An optional title for the output grid"), false, // this
                                                                    // parameter
                                                                    // is
                                                                    // optional
         0, 1, "raster", null);
   /**
    * The source of values for cells in the output grid coverage. This is either
    * the name ({@code String}) of a numeric feature property or a CQL
    * expression that can be evaluated to a numeric value.
    * <p>
    * This parameter is mandatory.
    */
   static final Parameter<String> ATTRIBUTE = new Parameter<String>("attribute", String.class, Text.text("Attribute"),
         Text.text("The feature attribute to use for raster cell values"), true, // this
                                                                                 // parameter
                                                                                 // is
                                                                                 // mandatory
         1, 1, null, null);
   /**
    * The bounds, in world coordinates, of the area to be rasterized. Optional:
    * if not provided the bounds of the input FeatureCollection will be used.
    */
   static final Parameter<Envelope> BOUNDS = new Parameter<Envelope>("bounds", Envelope.class, Text.text("Bounds"),
         Text.text("Bounds of the area to rasterize"), false, // this parameter
                                                              // is optional
         0, 1, null, null);
   /**
    * The result of the operation is a FeatureCollection. This can be the input
    * FeatureCollection, modified by the process or a new FeatureCollection.
    */
   static final Parameter<GridCoverage2D> RESULT = new Parameter<GridCoverage2D>("result", GridCoverage2D.class,
         Text.text("Result"), Text.text("Rasterized features"));
   static final Map<String, Parameter<?>> resultInfo = new LinkedHashMap<String, Parameter<?>>();
   static {
      resultInfo.put(RESULT.key, RESULT);
   }

   @Override
   protected void addParameters(Map<String, Parameter<?>> parameters) {
      parameters.put(BOUNDS.key, BOUNDS);
      parameters.put(ATTRIBUTE.key, ATTRIBUTE);
      parameters.put(RASTER_WIDTH.key, RASTER_WIDTH);
      parameters.put(RASTER_HEIGHT.key, RASTER_HEIGHT);
      parameters.put(TITLE.key, TITLE);
   }

   public InternationalString getTitle() {
      return Text.text("Rasterize features");
   }

   public InternationalString getDescription() {
      return Text.text("Rasterize all or selected features in a FeatureCollection");
   }

   public VectorToRasterProcess create() {
      return new VectorToRasterProcess(this);
   }

   public Map<String, Parameter<?>> getResultInfo(Map<String, Object> parameters) throws IllegalArgumentException {
      return Collections.unmodifiableMap(resultInfo);
   }

   public boolean supportsProgress() {
      return true;
   }

   public String getVersion() {
      return VERSION;
   }
}
