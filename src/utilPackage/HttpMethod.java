package utilPackage;

public enum HttpMethod 
{
    POST("POST"), 
    GET("GET"), 
    DELETE("DELETE"), 
    UPDATE("UPDATE");
 
    private String mtd;
 
    HttpMethod(String mtd) {
        this.mtd = mtd;
    }
 
    public String getMtd() {
        return this.mtd;
    }
}
