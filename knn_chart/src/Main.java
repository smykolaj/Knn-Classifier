import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main
{
    static int k = 60;

    public static void main(String[] args)
    {
//        Base base = new Base("iris.data");
//        System.out.print("Test using test data: ");
//        System.out.println(base.produceAccuracy("iris.test.data", k));
//
//        System.out.print("Test using training data: ");
//        System.out.println(base.produceAccuracy("iris.data", k));
//        base.produceAccuracy("iris.data", k);

        Base base = new Base("wdbc.data");
        System.out.print("Test using test data: ");
        System.out.println(base.produceAccuracy("wdbc.test.data",k));
        System.out.print("Test using training data: ");
        System.out.println(base.produceAccuracy("wdbc.data",k));
        Map<Integer, Double> accuracyByK = new HashMap<>();
        for (int i = 1; i <= base.getVectors().size(); i++){
            accuracyByK.put(i, base.produceAccuracy("wdbc.test.data", i));
        }
        KnnAccuracyPlot.plotAccuracy(accuracyByK);
        //startMenu(base);

    }

    public static void startMenu(Base base)
    {
        System.out.println("Would you like to check a single vector?" +
                "\nEnter 0 to exit" +
                "\nEnter 1 to check a vector");
        Scanner sc = new Scanner(System.in);

        String choice = sc.next();
        while (!choice.equals("0"))
        {

            switch (choice.trim())
            {
                case "0" ->
                {
                    break;
                }
                case "1" -> base.checkSingleVector(k);
                default ->
                {
                }

            }

            System.out.println("Would you like to check a single vector?" +
                    "\nEnter 0 to exit" +
                    "\nEnter 1 to check a vector");
            choice = sc.next();

        }
    }




}

