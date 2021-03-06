import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;
import java.util.Scanner;

public class mongoconnect {
    private static void choice_input(){
        System.out.println("** Student Details Tracker **\n" +
                "1. Insert data into database\n" +
                "2. Update database documents\n" +
                "3. Delete database documents\n" +
                "4. Show database collections\n" +
                "5. Exit");
    }

    public static void main(String[] args) {
        String key, value,roll,name,dept,cgpa,placedin;

        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase db = mongo.getDatabase("testDb");
            MongoCollection collection = db.getCollection("dummyColl");
            do{
                choice_input();
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice){
                    case 1:
                        Document document = new Document();

                        System.out.println("Enter rollno: ");
                        scanner.nextLine();
                        roll = scanner.nextLine();
                        document.put("rollno", roll);
                        System.out.println("Enter name: ");
                        name = scanner.nextLine();
                        document.put("name", name);

                        System.out.println("Enter department: ");
                        dept = scanner.nextLine();
                        document.put("department", dept);

                        System.out.println("Enter CGPA: ");
                        cgpa = scanner.nextLine();
                        document.put("cgpa", cgpa);

                        System.out.println("Enter Company where placed: ");
                        placedin = scanner.nextLine();
                        document.put("placedin", placedin);

                        collection.insertOne(document);
                        break;
                    case 2:
                        Document searchObj = new Document();
                        System.out.println("Enter roll no. of entry which you wish to update: ");
                        value = scanner.next();
                        searchObj.put("rollno", value);
                        Document newObj = new Document();

                        newObj.put("rollno", value);

                        System.out.println("Enter name: ");
                        scanner.nextLine();
                        name = scanner.nextLine();
                        newObj.put("name", name);

                        System.out.println("Enter department: ");
                        dept = scanner.nextLine();
                        newObj.put("department", dept);

                        System.out.println("Enter CGPA: ");
                        cgpa = scanner.nextLine();
                        newObj.put("cgpa", cgpa);

                        System.out.println("Enter Company where placed: ");
                        placedin = scanner.nextLine();
                        newObj.put("placedin", placedin);


                        collection.replaceOne(searchObj, newObj);
                        break;
                    case 3:
                        System.out.println("Enter the rollno. of the entry you wish to delete: ");
                        value = scanner.next();
                        Document removableObj = new Document();
                        removableObj.put("rollno", value);
                        collection.deleteOne(removableObj);
                        break;
                    case 4:
                        MongoCursor cursorDoc = collection.find().cursor();
                        if(!cursorDoc.hasNext())
                        {
                            System.out.println("\t** Empty Database!! **\n");
                        }
                        while (cursorDoc.hasNext()) {
                            System.out.println(cursorDoc.next());
                        }
                        break;
                    case 5:
                        System.exit(0);
                        break;
                }
            } while(choice != 5);
        } catch ( MongoException e) {
            e.printStackTrace();
        }
    }
}
