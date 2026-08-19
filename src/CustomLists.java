import java.util.function.Supplier;

public class CustomLists {

    private CustomLists() {

    }

    @SafeVarargs
    public static <E, L extends CustomList<E>> L of(Supplier<L> to, E... elements) {
        L customList = to.get();

        for (E element: elements) {
            customList.add(element);
        }

        return customList;
    }

    public static <E, L extends CustomList<E>> CustomList<E> toList(CustomList<E> from, Supplier<L> to) {
        L customList = to.get();

        for (E element: from) {
            customList.add(element);
        }

        return customList;
    }
}
