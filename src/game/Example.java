package game;

import com.fitincontact.engine.api.Generator;
import com.fitincontact.engine.main.object.*;

import java.io.IOException;

import static com.fitincontact.engine.Utils.pl;

public class Example {

//    private final Generator2 generator;
//
//    public Example() {
//        this.generator = new GeneratorObject();
//    }

    final Generator generator = new Generator();
    final Person person = generator.newPerson();

    public static void main(final String[] args) throws IOException {

        final Generator generator = new Generator();
        final Person person = generator.newPerson();


        final Inventory inventory = generator.newInventory();
        final Item inv_1 = generator.newItem(
                "карта",
                true,
                "карта",
                "",
                "",
                "карта сокровищ",
                "посмотрим на карту"
        );
        inventory.add(inv_1);
        inv_1.setActItem((r, inv) -> {
            pl(r.getDescription());
        });

        final Room r1 = generator.newRoom(
                "комната",
                "моя комната",
                "уютная комната 20 квадратов"
        );

        final Item i1_1 = generator.newItem(
                "лупа",
                true,
                "лупа",
                "Кажется на столе из под кипы книг торчит ручка лупы.",
                "я ее взял",
                "очень сильная лупа",
                "я использовал лупу"
        );
        final Item i1_2 = generator.newItem(
                "молоток",
                true,
                "молоток",
                "В углу почему-то валяется молоток.",
                "я взял молоток",
                "тяжелый молоток",
                "я ударил молотком"
        );
        final Item i1_3 = generator.newItem(
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

        final Room r2 = generator.newRoom(
                "коридор",
                "маленький коридор",
                "Здесь зеркало и шкаф"
        );

        final Room r3 = generator.newRoom(
                "зала",
                "большая зала",
                "тут мы собираемся всей семьей"
        );

        final Way w1_1 = generator.newWay(
                r2, "в коридор"
        );

        final Way w1_2 = generator.newWay(
                r3, "в зал"
        );

        r1.add(w1_1);
        r1.add(w1_2);


        generator.newCore(person, r1, inventory).start();
    }

    private void go(final Room room) {
        person.go(room);
    }
    /*
     * https://stackoverflow.com/questions/17913409/what-is-a-sam-type-in-java
     * https://stackoverflow.com/questions/4685563/how-to-pass-a-function-as-a-parameter-in-java
     * https://techndeck.com/how-to-pass-function-as-a-parameter-in-a-method-in-java-8/
     * */
}
