package bg.tu_varna.sit.Parser;


import bg.tu_varna.sit.Dimensions;


import java.util.Map;

public class JSONtoDimensions extends JSONParser implements  JSONParserInterface{
    private Map<String, Object> json;

    public void parseJSON(Map<String, Object> json) {
        this.json = json;
    }

    @Override
    public Dimensions parseJSON(String jsonString){
        this.json = parseObject(jsonString);
        return toDimensions();
    }

    public Dimensions toDimensions(){
        double length = 0;
        double width = 0;
        double height = 0;

        Dimensions.Builder builder = new Dimensions.Builder();


        if (json.containsKey("length"))
        {
                length = ((Number) json.get("length")).doubleValue();
                builder.length(length);
        }
        if (json.containsKey("width"))
        {
                width = ((Number) json.get("width")).doubleValue();
                builder.width(width);
        }
        if (json.containsKey("height")){
                height = ((Number) json.get("height")).doubleValue();
                builder.height(height);
        }

        if (length<0 || height<0 || width<0)
            throw new IllegalArgumentException("Negative dimensions not supported");

        Dimensions dimensions = builder.build();

        dimensions.setOrder(json);
        return dimensions;
    }
}
