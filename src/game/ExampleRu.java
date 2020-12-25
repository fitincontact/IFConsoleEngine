package game;

import com.fitincontact.engine.api.Act;
import com.fitincontact.engine.api.Generator;
import com.fitincontact.engine.api.Phrase;
import com.fitincontact.engine.api.Use;
import com.fitincontact.engine.main.format.PreFormat;
import com.fitincontact.engine.main.object.*;
import com.fitincontact.engine.main.variable.L;
import com.fitincontact.engine.main.variable.S;

import java.io.Serializable;

public final class ExampleRu {

    public static void pl(final String s) {
        Generator.pl(s);
    }

    public static void p(final String s) {
        Generator.p(s);
    }

    public static void main(final String[] args) throws Exception {
        final Generator generator = new Generator();
        final RR r = new RR();

        final L l1 = generator.newLong(111111111);
        //p(l1.toString());
        //System.out.println(l1.hashCode());
        l1.set(222222222);
        //p(l1.toString());
        //System.out.println(l1.hashCode());
        l1.set(222222222);
        //p(l1.toString());
        //System.out.println(l1.hashCode());
        final S sss = generator.newString("asdfg dghhhhhhh  uuuuuu t5y6u7i8 jjjjjjjj");


        final Phrase ph1 = (Phrase & Serializable) (room, inventory) -> {
            pl("где волшебник?");
            return true;
        };

        final Phrase ph2 = (Phrase & Serializable) (room, inventory) -> {
            pl("где волшебник");
            return true;
        };

        System.out.println(ph1);
        System.out.println(ph2);
        System.out.println(ph2);

        final Dialog dlg1 = generator.newDialog(
                "я начал разговор c дворецким"
        );
        {
            final Dialog dlg1_1 = dlg1.addContinues(new Dialog(
                    "Доложите о моем приезде.",
                    "Господин просил не мешать."
            ));
            final Dialog dlg1_2 = dlg1.addContinues(new Dialog("Впустите меня!", "И не подумаю!"));
            final Dialog dlg1_3 = dlg1.addContinues(new Dialog(
                    "Где ваш хозяин?",
                    "Боюсь вам сегодня не назначено. Ступайте, сударь."
            ));
            {
                final Dialog dlg1_1_1 = dlg1_3.addContinues(new Dialog("Именем короля, впустите!", "Я плохо слышу."));
                final Dialog dlg1_1_2 = dlg1_3.addContinues(new Dialog(
                        "Ну, смерд, отведаешь ты у меня плетей!",
                        "Кажется я начинаю вас понимать..."
                ));
                {
                    final Dialog dlg1_1_2_1 = dlg1_1_2.addContinues(new Dialog(
                            "И что дальше?",
                            "Входите пожалуйста, здесь вам искренне рады!"
                    ));
                }
            }
        }

//RR jjj= {"sss","xxxxx",new Object[]{"",ph1}};
        final Object[] rwww = {
                new Object[]{"sss", "xxxxx"},
                new Object[]{
                        "tttt", ph1,
                        new Object[]{ph2, "kjhgfdsa"}
                }
        };

//        final Object[] rwww2 = {
//                r.newRR({"sss", ph1}),
//                new Object[]{
//                        "tttt", ph1,
//                        new Object[]{ph2, "kjhgfdsa"}
//                }
//        };

        //final Object[] sdsd = {"sss","xxxxx",r.newRR((Object[]){"",ph1}),};


        final String[][][] threeD_arr = new String[10][20][20];
        final int[] cats = {2, 5, 7, 8, 3, 0};


        final PreFormat preFormat = generator.newPreFormat();
        preFormat.setGameName("БОЯРКА СПИРТ И МАГИЯ или ПО СЛЕДАМ ВОЛШЕБСТВА");
        preFormat.setSaveName("save");
        preFormat.setUnDefininedWordIfNotContains(" - не вижу здесь чего-то похожего");
        preFormat.setUnDefininedWordIfContains(" - это невозможно использовать");
        preFormat.setUnDefininedWordUse(" - нужно попробовать что-то другое");
        generator.setInstance(preFormat);

        final Person person = generator.newPerson("Аруй");
        final Game game = generator.newGame();
        game.add(person);
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

        final Act butlerAct = (Act & Serializable) (room, inv) -> {
            dlg1.start();
            return true;
        };

        final Item butler = generator.newItem(
                "дворецкий",
                false,
                "",
                "Передо мной стоит дворецкий, наглая ухмылка не сходит с его лица, не нравиться он мне.",
                "",
                "",
                ""
        );
        butler.add(butlerAct);

        main_room.add(butler);

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
                ""
        );

        final Item table = generator.newItem(
                "стол",
                false,
                "",
                "Красивый резной стол.",
                "под столом пусто. Зазул, ау!",
                "",
                ""

        );

        final Item trash = generator.newItem(
                "хлам",
                false,
                "",
                "Тут полно всякого хлама.",
                "там в завалах сам черт ногу сломит, не хочу там копаться, пойду где светло и чисто",
                "",
                ""
        );

        final Item magnifier = generator.newItem(
                "лупа",
                true,
                "лупа",
                "Кажется на столе из под кипы книг торчит ручка лупы.",
                "я ее взял",
                "очень сильная лупа",
                "я использовал лупу"
        );

        final Item note = generator.newItem(
                "записка",
                true,
                "записка",
                "Иголкой к столу пришпилена записка.",
                "Кажется это не иголка а миниатюная сабля, а записочку возьму",
                "какие-то мелкие еле различимые каракули, это что записка для мышей?",
                "\n\"в рот, компот и банку шпрот\nпропади же обормот\"\nчто это может значить?"
        );

        final Use useNote = (Use & Serializable) (room, items) -> {
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
                "я ударил молотком"
        );

        final Item alcohol = generator.newItem(
                "бутыль",
                false,
                "",
                "У стены стоит большая, величиной с бочку, бутыль с прозрачной жидкостью, с этикеткой \"ОСНОВА ВСЕГО\".",
                "отвинтил крышку и чуть не свалился от резкого запаха, это спирт, так пахнет пятница мой друг",
                "",
                ""
        );

        final Item key = generator.newItem(
                "ключ",
                true,
                "ключ",
                "Пол покрывает слой даже думать не хочу чего, рожь можно сеять, из под луковой шелухи блестит ключ.",
                "возьму этот ключ, может пригодиться",
                "надо бы поискать дверь к которой он подойдет",
                "ключ подошел к двери, я открыл её"
        );

        final Item window = generator.newItem(
                "окно",
                false,
                "",
                "Огромное окно высотой в два человечьих роста.",
                "за окном лес до самого горизонта",
                "",
                ""
        );
        final Use useWindow = (Use & Serializable) (room, items) -> {
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
                if (!inventory.isHave(hammer)) {
                    hammer.move(labor, inventory);
                }
                room.toStrRoom(inventory);
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
        final Use useWayPanty = (Use & Serializable) (room, items) -> {
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

        generator.newCore(main_room, inventory).start();
    }
}