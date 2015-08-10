package business;

/**
 *
 * @author renannp
 */
public class Edge 
{
    private final Vertex vertex;
    private final Integer distance;
    
    public Edge(Vertex v, Integer distance)
    {
        this.vertex = v;
        this.distance = distance;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public Integer getDistance() {
        return distance;
    }
}
