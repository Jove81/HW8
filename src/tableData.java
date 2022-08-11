import java.io.*;
import java.util.HashMap;


public class tableData {
    public static void toTable(HashMap[] H){
        System.out.printf(" %-5s | %-30s| %-20s| %-20s| %n", "№","Наименование товара", "Цена руб.", "Остаток на складе");
        System.out.println("+------+-------------------------------+---------------------+---------------------+");
        for(int i =0; i<H.length; i++){
            System.out.printf("| %-5s| %-30s| %-20s| %-20s| %n", i, H[i].get("name"), H[i].get("cost"), H[i].get("quan"));
            //System.out.println("+-------------------------------+---------------------+---------------------+");

        }
        System.out.println("+------+-------------------------------+---------------------+---------------------+");
    }

    public static void addNewProduct(File file, String name, String cost, String quan) {
        //String lineSeparator = System.getProperty("line.separator");
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath(), true)));
            writer.println(name + "," + cost + "," + quan);
            writer.close();
            System.out.println("Товар добавлен");
        } catch (IOException e){
            System.out.println("Ошибка добавления");
        }
    }

    public static void deleteLine(File file, int str) {

        File tempFile = new File(file.getParent()+"/shop.tmp");

        try {
            if(!tempFile.exists()) tempFile.createNewFile();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(tempFile.getAbsolutePath())));
//----------перезапись базы данных во временный файл------------------------
            int i = 0;
            HashMap[] H = Parser.parsDB(file);
            while (H.length > i){
                if(i!=str) {
                    writer.println(H[i].get("name") + "," + H[i].get("cost") + "," + H[i].get("quan"));
                }
                i ++;
            }
            writer.close();
//----------Из временного файла обратно в базу------------------------
            HashMap[] HT = Parser.parsDB(tempFile);
            PrintWriter rewriter = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
            int j = 0;
            while (HT.length > j){
                rewriter.println(HT[j].get("name") + "," + HT[j].get("cost") + "," + HT[j].get("quan"));
                j ++;
            }
            rewriter.close();
            tempFile.delete();
            toTable(Parser.parsDB(file));

        }catch (IOException e){
            System.out.println("Ошибка создания временного файла");
        }


    }

    public static void changeProductQuantity(File file, String str, String quan, String math)   {
        File tempFile = new File(file.getParent()+"/shop.tmp");
            int strInt = Integer.parseInt(str);
            int quanInt = Integer.parseInt(quan);
            int result;
        try {
            if(!tempFile.exists()) tempFile.createNewFile();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(tempFile.getAbsolutePath())));
//----------перезапись базы данных во временный файл------------------------
            int i = 0;
            HashMap[] H = Parser.parsDB(file);
            String s = (String) H[strInt].get("quan");
            int curentQuan = Integer.parseInt(s);
            if(math =="+"){
                result = curentQuan + quanInt;
            } else {
                result = curentQuan - quanInt;
            }

            if(math =="-" && curentQuan>=quanInt) {
                H[strInt].put("quan", Integer.toString(result));
            }else if(math =="+") {
                H[strInt].put("quan", Integer.toString(result));
            }else {
                System.out.println("Недостаточно товара");
            }
            while (H.length > i){
                    writer.println(H[i].get("name") + "," + H[i].get("cost") + "," + H[i].get("quan"));
                i ++;
            }
            writer.close();
//----------Из временного файла обратно в базу------------------------
            HashMap[] HT = Parser.parsDB(tempFile);
            PrintWriter rewriter = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
            int j = 0;
            while (HT.length > j){
                rewriter.println(HT[j].get("name") + "," + HT[j].get("cost") + "," + HT[j].get("quan"));
                j ++;
            }
            rewriter.close();
            tempFile.delete();
            toTable(Parser.parsDB(file));

        }catch (IOException e){
            System.out.println("Ошибка");
        }

    }

}
