package bg.tu_varna.sit;

import java.util.LinkedHashMap;
import java.util.Map;

public class Dimensions {
    private Double length;
    private Double width;
    private Double height;
    private Map<String, Object> order;


    private Dimensions(Double length, Double width, Double height) {
        this.length = length;
        this.width = width;
        this.height = height;
        order = new LinkedHashMap<>();
    }

    public static class Builder {
        private Double length;
        private Double width;
        private Double height;

        public Builder length(Double length) {
            this.length = length;
            return this;
        }

        public Builder width(Double width) {
            this.width = width;
            return this;
        }

        public Builder height(Double height) {
            this.height = height;
            return this;
        }

        public Dimensions build() {
            return new Dimensions(length, width, height);
        }
    }

    public Dimensions() {
        order = new LinkedHashMap<>();
    }

    public void setOrder(Map<String, Object> order) {
        this.order = order;
    }

    public Double getLength() {
        return length;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }

    public Map<String, Object> getOrder() {
        return order;
    }


    public void setLength(Double length) {
        this.length = length;
        if (order.containsKey("length"))
            order.replace("length", length);
        else
            order.put("length", length);
    }

    public void setWidth(Double width) {
        this.width = width;
        if (order.containsKey("width"))
            order.replace("width", width);
        else
            order.put("width", width);
    }

    public void setHeight(Double height) {
        this.height = height;
        if (order.containsKey("height"))
            order.replace("height", height);
        else
            order.put("height", height);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{Length: ");
        if (length == null)
            sb.append("Not specified ");
        else
            sb.append(length);
        sb.append("\tWidth: ");
        if (width == null)
            sb.append("Not specified ");
        else
            sb.append(width);
        sb.append("\tHeight: ");
        if (height == null)
            sb.append("Not specified ");
        else
            sb.append(height);
        sb.append("}");
        return sb.toString();
    }

    public String toJSON() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, Object> o : order.entrySet()) {
            sb.append("\n\t\t\t\"").append(o.getKey()).append("\": ").append(o.getValue());
            if (i != order.size()) {
                sb.append(",");
            }
            i++;
        }
        sb.append("\n\t\t}");
        return sb.toString();
    }
}