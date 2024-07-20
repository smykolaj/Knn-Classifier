import java.util.*;


public class Vector
{
    private String name;
    private ArrayList<Double> values = new ArrayList<>();
    private HashMap<Vector, Double> distances = new HashMap<>();
    private String info;


    public Vector(String info)
    {
        this.info = info;
        String[] splittedInfo = info.split(",");
        for (int i = 0; i < splittedInfo.length - 1; i++)
        {
            values.add(Double.valueOf(splittedInfo[i]));
        }
        name = splittedInfo[splittedInfo.length - 1];
    }

    public Vector calculateDistances(Base base)
    {
        for (Vector v : base.getVectors())
        {
            if (this != v)
            {
                distances.put(v, distanceBetweenTwoVectors(this, v));
            }
        }
        return this;
    }


    public String getName()
    {
        return name;
    }

    public int getDimension()
    {
        return values.size();
    }


    public boolean realNameCorrespondsToCalculatedByK(int k)
    {
        return calculatedName(k).equals(this.name);
    }

    public String calculatedName(int k)
    {
        List<Map.Entry<Vector, Double>> sortedEntries = new ArrayList<>(distances.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue());


        Map<String, Integer> nameFrequency = new HashMap<>();
        for (int i = 0; i < Math.min(k, sortedEntries.size()); i++)
        {
            Map.Entry<Vector, Double> entry = sortedEntries.get(i);
            String name = entry.getKey().getName();
            nameFrequency.put(name, nameFrequency.getOrDefault(name, 0) + 1);
        }


        return Collections.max(nameFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();

    }


    public double distanceBetweenTwoVectors(Vector v1, Vector v2)
    {
        double distance = 0;
        for (int i = 0; i < v1.values.size(); i++)
        {
            distance += Math.pow((v1.values.get(i) - v2.values.get(i)), 2);
        }
        return Math.sqrt(distance);
    }
}
