import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Base
{
    private ArrayList<Vector> vectors = new ArrayList<>();
    private Scanner scannerFromFile = null;
    private Scanner scannerForUserInput = new Scanner(System.in);


    public Base(String path)
    {
        createBase(new File(path));
    }

    public ArrayList<Vector> getVectors()
    {
        return vectors;
    }

    private void createBase(File path)
    {
        Scanner sc = null;
        try
        {
            sc = new Scanner(path);
            while (sc.hasNextLine())
            {
                String data = sc.nextLine();
                vectors.add(new Vector(data));
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public double produceAccuracy(String pathForTest, int k)
    {

        double allEntries = 0.0;
        double correct_entries = 0.0;
        Scanner sc = null;
        try
        {
            sc = new Scanner(new File(pathForTest));
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        while (sc.hasNextLine())
        {
            String data = sc.nextLine();
            if (new Vector(data).calculateDistances(this).realNameCorrespondsToCalculatedByK(k)) correct_entries++;
            allEntries++;

        }
        return (correct_entries / allEntries * 100);


    }

    public void checkSingleVector(int k)
    {
        System.out.print("Please enter a vector in such format:");

        for (int i = vectors.get(0).getDimension(); i > 0; i--)
        {
            System.out.print("x.x,");
        }
        System.out.println("\b");
        String newVectorValues = scannerForUserInput.next().trim();
        try
        {
            Vector newVector = new Vector(newVectorValues + ",Unknown");
            newVector.calculateDistances(this);
            System.out.println("New vector is most probably: " + newVector.calculatedName(k));
        } catch (Exception e)
        {
            System.out.println("Error processing the vector. Please ensure it matches the expected format.");
        }
    }


}
