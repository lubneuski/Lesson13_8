import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> alldocs = new ArrayList<>();
        String a = "src/Doc1";
        String b = "src/Doc2";
        String c = "src/Doc3";
        alldocs.add(a);
        alldocs.add(b);
        alldocs.add(c);

        Set<String> sort = new HashSet<>();     //Из allDocs считываем ссылки и передаем содержимое файлов в sort
        for (String elem : alldocs) {
            BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(elem))));
            String line;
            while ((line = br.readLine()) != null)
                sort.add(line);
        }

        Map<String, String> fin = new HashMap<>();      //Из sort считываем строкиБ проверяем на валидность и записываем в fin
        for (String elem : sort) {
            if (elem.matches("[A-Za-z0-9]+") && elem.length() == 15 && (elem.startsWith("contract") || elem.startsWith("docnum"))) {
                fin.put(elem, "Подходит");
            } else {
                if (elem.length() == 15 && (!(elem.startsWith("contract") || elem.startsWith("docnum")))) {
                    fin.put(elem, "Не начинается с нужной последовательности");
                } else if (!elem.matches("[A-Za-z0-9]+")) {
                    fin.put(elem, "Содержит недопустимые символы");
                } else {
                    fin.put(elem, "Не состоит из пятнадцати символов");
                }
            }
        }

        BufferedWriter bwV = new BufferedWriter(new FileWriter("src/Document"));    //Из fin записываем в Document
        for (Map.Entry<String, String> entry : fin.entrySet()) {
            bwV.write(entry.getKey() + " - " + entry.getValue() + "\n");
            bwV.flush();
        }

    System.out.println("Операция прошла успешно");
    }
}