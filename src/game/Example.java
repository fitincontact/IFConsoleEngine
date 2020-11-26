package game;

import com.fitincontact.engine.api.Generator;
import com.fitincontact.engine.api.Use;
import com.fitincontact.engine.main.object.*;

import java.io.IOException;

import static com.fitincontact.engine.Utils.pl;

public final class Example {

    private final Generator generator = new Generator();
    private final Game game = getGenerator().newPerson();

    public static void main(final String[] args) throws IOException {

        final Generator generator = new Generator();
        final Game game = generator.newPerson();
        final Inventory inventory = generator.newInventory();

        final Room main_room = generator.newRoom(
                "menu",
                "БОЯРКА СПИРТ И МАГИЯ или ПО СЛЕДАМ ВОЛШЕБСТВА",
                "Волшебник Зазул жаден, так считает мой король, пославший меня, своего верного " +
                "придворного, за средством от золотожорного дракона - \"Друг мой Аруй, многие столетия " +
                "наши земли не знали такого напастья, ненасытный змей разоряет Наше царство," +
                "только колдовская настойка багульника и благородного боярышника избавит нас от него\". Цена " +
                "её велика и было принято решение экспроприировать зелье, вот только прознав про это, Зазул сбежал " +
                "как только ему доложили о моем приезде. Что ж, как ищейка мне придется идти по его следам, " +
                "смекалкой и чутьем отыскать его, где бы этот скряга не спрятался."
        );

        //---//

        final Room hall = generator.newRoom(
                "Зал",
                "ЗАЛ",
                "Этот огромный зал является приемной волшебника, и по совместительству видимо складом рухляди."
        );

        final Way wayHall = generator.newWay(hall, "в зал");
        main_room.add(wayHall);

        final Item cabinet = generator.newItem(
                "шкаф",
                false,
                "",
                "Высокий черный шкаф, на дверцах изображения чудовищ.",
                "дверцы не открываются, хоть шкаф и большой не думаю что волшебник там",
                "",
                "",
                null,
                null
        );

        final Item table = generator.newItem(
                "стол",
                false,
                "",
                "Красивый резной стол.",
                "под столом пусто. Зазул, ау!",
                "",
                "",
                null,
                null
        );

        final Item trash = generator.newItem(
                "хлам",
                false,
                "",
                "Тут полно всякого хлама.",
                "там в завалах сам черт ногу сломит, не хочу там копаться, пойду где светло и чисто",
                "",
                "",
                null,
                null
        );

        final Item magnifier = generator.newItem(
                "лупа",
                true,
                "лупа",
                "Кажется на столе из под кипы книг торчит ручка лупы.",
                "я ее взял",
                "очень сильная лупа",
                "я использовал лупу", null, null
        );

        final Item note = generator.newItem(
                "записка",
                true,
                "записка",
                "Иголкой к столу пришпилена записка.",
                "Кажется это не иголка а миниатюная сабля, а записочку возьму",
                "какие-то мелкие еле различимые каракули, это что записка для мышей?",
                "\n\"в рот, компот и банку шпрот\nпропади же обормот\"\nчто это может значить?",
                null,
                null
        );

        final Use useNote = (room, items) -> {
            if (items.size() == 2 && items.contains(magnifier)) {
                pl("теперь можно различить буквы, вот что тут написано: " + note.getUseTxt());
                return true;
            }
            return false;
        };
        note.add(useNote);

        hall.add(table, note, trash, cabinet);

        //---//

        final Room labor = generator.newRoom(
                "лаборатория",
                "ЛАБОРАТОРИЯ",
                "Реторты и склянки, бадьи с водорослями, корзина живых лягушек, " +
                "клетки на полу и на стенах с чудными тварями, стеклоглазы, рыгуны, покашливают и сопят, " +
                "но все это плохо видно, едкая дымка нависает надо всем, " +
                "ядовито-синим горят комфорки под колбами, это из колб с кипящим варевом " +
                "поднимается этот туман, колдун явно только-что был здесь, пока не поймаю он так и продолжит " +
                "пакостничать и расствалять ловушки."
        );

        hall.add(generator.newWay(labor, "дверь"));
        labor.add(generator.newWay(hall, "обратно в зал"));

        final Item hammer = generator.newItem(
                "молоток",
                true,
                "молоток",
                "В углу почему-то валяется молоток.",
                "я взял молоток",
                "тяжелый молоток",
                "я ударил молотком",
                null,
                null
        );

        final Item alcohol = generator.newItem(
                "бутыль",
                false,
                "",
                "У стены стоит большая, величиной с бочку, бутыль с прозрачной жидкостью, с этикеткой \"ОСНОВА ВСЕГО\".",
                "отвинтил курышку и чуть не свалился от резкого запаха, это спирт, так пахнет пятница мой друг",
                "",
                "",
                null,
                null
        );

        final Item key = generator.newItem(
                "ключ",
                true,
                "ключ",
                "Пол покрывает слой даже думать не хочу чего, рожь можно сеять, из под луковой шелухи блестит ключ.",
                "возьму этот ключ, может пригодиться",
                "надо бы поискать дверь к которой он подойдет",
                "ключ подошел к двери, но дверь так заржавела что пришлось приложить все силы чтобы " +
                "открыть ее",
                null,
                null
        );

        final Item window = generator.newItem(
                "окно",
                false,
                "",
                "Огромное окно высотой в два человечьих роста.",
                "за окном лес до самого горизонта",
                "",
                "",
                null,
                null
        );
        final Use useWindow = (room, items) -> {
            if (items.size() == 2 && items.contains(hammer)) {
                pl("разнес окно в дребезги, в душное пространство лаборатории ворвался лесной ветер, ветер хвои, " +
                   "душицы и смородины, ветер странствий, едкий дым развеяло");
                labor.add(key);
                labor.add(magnifier);
                labor.remove(window);
                labor.setDescription(
                        "Реторты и склянки, бадьи с водорослями, корзина живых лягушек, " +
                        "клетки на полу и на стенах с чудными тварями, стеклоглазы. "
                );
                if(!inventory.isHave(hammer)){
                    hammer.move(labor,inventory);
                }
                room.pr(inventory);
                return true;
            }
            return false;
        };
        window.add(useWindow);

        labor.add(hammer, alcohol, window);
        //---//

        final Room panty = generator.newRoom(
                "кладовка",
                "КЛАДОВКА",
                "Множество ящиков и шкафов, все они заполнены сушеными травами и прочими алхимическими " +
                "реагентами"
        );
        final Way wayPanty = generator.newWay(
                panty, "ржавая дверь"
        );
        wayPanty.setLock(true);
        wayPanty.setLockTxt("не открывается, может просто нужен ключ?");
        final Use useWayPanty = (room, items) -> {
            if (items.size() == 1 && items.contains(key)) {
                wayPanty.setLock(false);
                pl(key.getUseTxt());
                return true;
            }
            pl("это не поможет");
            return false;
        };
        wayPanty.add(useWayPanty);
        labor.add(wayPanty);

        final Room r3 = generator.newRoom(
                "зала",
                "большая зала",
                "тут мы собираемся всей семьей"
        );

        final Way w1_2 = generator.newWay(
                r3, "в зал"
        );

        generator.newCore(game, main_room, inventory).start();
    }

    private void go(final Room room) {
        game.go(room);
    }

    public Generator getGenerator() {
        return generator;
    }
    /*
     * https://stackoverflow.com/questions/17913409/what-is-a-sam-type-in-java
     * https://stackoverflow.com/questions/4685563/how-to-pass-a-function-as-a-parameter-in-java
     * https://techndeck.com/how-to-pass-function-as-a-parameter-in-a-method-in-java-8/
     * */
}
