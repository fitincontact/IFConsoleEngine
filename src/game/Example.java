package game;

import com.fitincontact.engine.*;

import java.io.IOException;


public class Example {
    public static void main(String[] args) throws IOException {

        final Generator generator = new Generator();

        final Inventory inventory = generator.newInventory();
        Item inv_1 = generator.newItem(
                "карта",
                true,
                "карта",
                "",
                "карта сокровищ",
                "карта сокровищ",
                "посмотрим на карту"
        );
        inventory.add(inv_1);

        Room r1 = generator.newRoom(
                "комната",
                "моя комната",
                "уютная комната 20 квадратов"
        );

        Item i1_1 = generator.newItem(
                "лупа",
                true,
                "большая лупа",
                "Кажется на столе из под кипы книг торчит ручка лупы.",
                "я ее взял",
                "",
                "я использовал лупу"
        );
        Item i1_2 = generator.newItem(
                "молоток",
                true,
                "тяжелый молоток",
                "В углу почему-то валяется молоток.",
                "я взял молоток",
                "",
                "я ударил молотком"
        );
        Item i1_3 = generator.newItem(
                "окно",
                false,
                "",
                "За шторами окно.",
                "я раздвинул шторы, за окном яркий день, весь город виден как на ладони, у дома магазин",
                "",
                ""
        );
        r1.add(i1_1);
        r1.add(i1_2);
        r1.add(i1_3);

        Room r2 = generator.newRoom(
                "коридор",
                "маленький коридор",
                "Здесь зеркало и шкаф"
        );

        Room r3 = generator.newRoom(
                "зала",
                "большая зала",
                "тут мы собираемся всей семьей"
        );

        Way w1_1 = generator.newWay(
                r2, "в коридор"
        );

        Way w1_2 = generator.newWay(
                r3, "в зал"
        );

        r1.add(w1_1);
        r1.add(w1_2);


        generator.newCore(r1,inventory).start();
    }
}
