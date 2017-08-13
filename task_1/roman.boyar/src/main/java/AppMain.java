//import org.apache.commons.codec.digest.DigestUtils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AppMain {



    /*
    Вам нужно реализовать в памяти структуру похожую на то, как сохраняет данные Git.

На занятии мы говорили о том, что один объект в такой базе может быть представлен элементом известной структуры данных Map, в котором ключом будет SHA-1 тех данных, для которых использован этот ключ. Чтобы получить SHA-1 используйте библиотеку commons-codec: https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/digest/DigestUtils.html#sha1Hex(java.lang.String)

Вам нужно реализовать следующие моменты:

Добавление нового элемента (не нужно записывать данные на диск, только в память)
Вывод данных по ключу
Вывод всех данных которые находятся в памяти в консоль
Вывод всех данных в виде файлов и папок на диск по аналогии с гитом (*)
Подумайте как вы будете реализовывать проблему, если у вас один элемент ссылается рекурсивно на предыдущий

Типы элементов, которые могут быть в Map в качестве значений:

commit (содержит хэш значение, которое является ключом для какого-то другого элемента)
tree (то же, что и commit)
blob (какой-то текст, можно представить обычной строкой)
     */


    public static HashMap<String, Element> hashMap = new HashMap();


    //Добавление нового элемента (не нужно записывать данные на диск, только в память)
    public static void addNewElementBlob(String namePar, String contentPar) {
        Element element = new Element();
        element.setName(namePar);
        element.setType(Element.TYPE.blob);
        element.setHash(DigestUtils.sha1Hex(contentPar));
        element.setContent(contentPar);

        hashMap.put(element.getHash(), element);
        System.out.println("successfully added file blob type");
        System.out.println(hashMap.get(element.getHash()));
    }
    public static void addNewElementTree(String namePar, String contentPar) {
        Element element = new Element();
        element.setName(namePar);
        element.setType(Element.TYPE.blob);
        element.setHash(DigestUtils.sha1Hex(contentPar));
        element.setContent(contentPar);

        hashMap.put(element.getHash(), element);
        System.out.println("successfully added file blob type");
        System.out.println(hashMap.get(element.getHash()));
    }


    //Вывод данных по ключу
    public static void listElements(String key) {
        System.out.println(hashMap.get(key));
    }

    //Вывод всех данных которые находятся в памяти в консоль
    public static void listAllElements() {
        Set<Map.Entry<String, Element>> entries = hashMap.entrySet();

        for (Map.Entry<String, Element> entry : entries) {
            System.out.println(entry);
        }
    }

    //Вывод всех данных в виде файлов и папок на диск по аналогии с гитом (*)
    public static void listAllElementsAsFoldersAndFilesOnHDD() {


        Set<Map.Entry<String, Element>> entries = hashMap.entrySet();

        for (Map.Entry<String, Element> entry : entries) {
            System.out.println(entry);
        }


    }


    public static void main(String[] args) {
        AppMain appMain = new AppMain();

        Scanner in = new Scanner(System.in);
        while (true) {

            System.out.println("Выполните одну из операций: ");
            System.out.println("addNewElementBlob:  1(String namePar, String contentPar)");
            System.out.println("addNewElementTreeOrCommit:  2(String namePar, String contentPar)");
            System.out.println("listElements(String key):  3(String key)");
            System.out.println("listAllElements(): 4 ");
            System.out.println("listAllElementsAsFoldersAndFilesOnHDD(): 5");
            String strTemp = in.nextLine();

            strTemp = strTemp.trim();

            if (strTemp.compareTo("exit") == 0) {
                break;
            } else if (strTemp.startsWith("1")) {

                String namePar, contentPar;

                String[] arrayTemp = strTemp.replace("(", "").replace(")", "").
                        substring(1).split(",");
                namePar = arrayTemp[0];
                contentPar = arrayTemp[1];

                appMain.addNewElementBlob(namePar, contentPar);
            } else if (strTemp.startsWith("3")) {

                String key = strTemp.replace("(", "").replace(")", "").
                        substring(1);

                appMain.listElements(key);
            } else if (strTemp.startsWith("4")) {

                appMain.listAllElements();
            } else if (strTemp.startsWith("5")) {

                appMain.listAllElementsAsFoldersAndFilesOnHDD();
            }

        }


    }


}
