import list.CustomArrayList;
import list.CustomLinkedList;
import list.CustomList;
import list.CustomLists;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<String> customArrayList = new CustomArrayList<>();

        CustomList<String> list = CustomLists.toList(customArrayList, CustomLinkedList::new);
    }
}