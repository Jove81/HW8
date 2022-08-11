import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class theShop {
    public static void main(String[] args) throws IOException {
        File file = new File("src/shop.csv");
        try {
            if(!file.exists()) file.createNewFile(); else System.out.println("File Exist");
        } catch (IOException e){
            System.out.println("Ошибка создания файла");
        }
        String[] MenyList = {"Добавить товар","вывести все товары","Удалить товар","Продажа","Выход"};
        String[] Column = {"Наименование товара", "Цена руб.", "Остаток на складе"};
        drawMeny(MenyList);

        Scanner scan = new Scanner(System.in);

        while (true) {
            String value = scan.nextLine();
            if (value.equals("1")) {
                System.out.println("Добавить новый - 1; Добавить количество - 2;");
                String subValue = scan.nextLine();
                if(subValue.equals("1")) {
                    String n = "";
                    String c = "";
                    String q = "";
                    String[] varl = new String[Column.length];
                    System.out.println(MenyList[0] + ":");
                    for (int i = 0; i < Column.length; i++) {
                        System.out.println(Column[i]);
                        String enter = scan.nextLine();
                        varl[i] = enter;

                        while (enter == null || enter.isEmpty() || enter.trim().isEmpty()) {// проверка на пробел или отсутствие ответа
                            System.out.println("!Empty Data");
                            System.out.println(Column[i]);
                            enter = scan.nextLine();
                            varl[i] = enter;
                        }
                        n = varl[0];
                        c = varl[1];
                        q = varl[2];
                    }
                    tableData.addNewProduct(file, n, c, q);

                }else if(subValue.equals("2")) {
                    String str="";
                    String q="";
                    System.out.println("Номер товара:");
                    str = scan.nextLine();
                    System.out.println("Добавить шт.:");
                    q = scan.nextLine();
                    System.out.println(str +" "+ q);
                    tableData.changeProductQuantity(file, str, q, "+");

                }else{System.out.println("Введите коррекстное значение");}
                drawMeny(MenyList);
            }
            if (value.equals("2")) {
                System.out.println(file.getName()+"; "+ new Date(file.lastModified()));
                tableData.toTable(Parser.parsDB(file));
                drawMeny(MenyList);
            }
            if (value.equals("3")) {
                System.out.println("Введите номер товара:");
                String v = scan.nextLine();
                int n = Integer.parseInt(v);
                tableData.deleteLine(file, n);
                drawMeny(MenyList);
            }
            if (value.equals("4")) {
                System.out.println("Номер товара:");
                String v = scan.nextLine();
                System.out.println("Количество:");
                String w = scan.nextLine();

                tableData.changeProductQuantity(file, v, w, "-");
                drawMeny(MenyList);
            }
            if (value.equals("5")) {
                System.out.println("The End");
                break;
            }
        }
    }

    public static void drawMeny(String [] menyArr){
        System.out.println("____Меню____");
        for (int i = 0; i<menyArr.length; i++){
            System.out.println(i+1 +". "+menyArr[i]);
        }
    }
}
