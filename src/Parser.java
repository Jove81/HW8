import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Parser {
    public static HashMap[] parsDB(File file) throws IOException {

        //File file = new File(fPath);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedReader reader2 = new BufferedReader(new FileReader(file));
        String text;
        String text2;
        int strCount=0;
        int counter=0;
        while ((text2 = reader2.readLine()) !=null){//считаем кол-во строк и записываем в переменную ?костыль?
            strCount++;
        }
        reader2.close();

        //Product[] products = new Product[strCount]; //objects array
        HashMap[] productsArr = new HashMap[strCount];

        while ((text = reader.readLine()) !=null){
            //if(counter>0){//пропускаем первую строку с заголовками
                //int cnt = counter-1;

                String Str[] = text.split(",");//считываем строку из базы и превращаем её в массив

                //products[cnt] = new Product(Str[0],Str[1],Str[2]);//создаём объект товара
                HashMap<String, String> prod = new HashMap<>();
                prod.put("name", Str[0]);
                prod.put("cost", Str[1]);
                prod.put("quan", Str[2]);
                productsArr[counter] = prod; //помещаем мапу в массив

                //System.out.println(productsArr[cnt].get("cost"));
                // ------------- если нужно создать объект класса, раскоментить: ------------------
//                System.out.println("Наименование "+ product[cnt].name);
//                System.out.println("Цена "+ product[cnt].cost);
//                System.out.println("Количество "+ product[cnt].quantity);
//                System.out.println("___________________________");

            //}
            counter++;
        }
        reader.close();
        return productsArr; //возвращаем массив списков товаров
    }
}
